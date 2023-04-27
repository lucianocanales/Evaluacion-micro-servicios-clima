package com.evaluation.weather.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evaluation.weather.dto.fiveDay.DailyForecastsDTO;
import com.evaluation.weather.dto.fiveDay.FiveDayDTO;
import com.evaluation.weather.dto.twelveHour.TwelveHourDTO;
import com.evaluation.weather.entity.WeatherEntity;
import com.evaluation.weather.model.LocationModel;
import com.evaluation.weather.repository.WeatherRepository;

@Service
public class WeatherService {

    @Value("${spring.external.service.five-day-url}")
    private String fiveDayUrl;

    @Value("${spring.external.service.twelve-hour-url}")
    private String twelveHourUrl;

    @Value("${spring.external.service.api-key}")
    private String apiKey;

    @Value("${spring.internal.service.location-url}")
    private String locationUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherRepository weatherRepository;

    
    /**
     * Este metodo connecta con el microservicio Location para buscar las ciudades
     * con sus respectivas Keys 
     * 
     * @param name
     * @return
     */
    private LocationModel getlocation(String name){
        String uri = locationUrl + name;
        LocationModel externalLocation = restTemplate.getForObject(uri, LocationModel.class);
        return externalLocation;
    }

    /**
     * Retorna toda la lista de Weather de la base de datos
     * precaucion puede traer todo y generar problemas de carga
     *  
     * @return
     * @deprecated
     */
    public List<WeatherEntity> getAll(){
        return weatherRepository.findAll();
    }

    /**
     * Retorna un solo registro de la base de datos a parti de un id
     * 
     * @param id
     * @return
     */
    public WeatherEntity getWeatherById(int id){
        return weatherRepository.findById(id).orElseGet(null);
    }

    /**
     * Sirve para guarad los datos en la base de datos
     * 
     * @param weatherEntity
     * @return
     */
    private WeatherEntity save(WeatherEntity weatherEntity){
        WeatherEntity newWeatherEntity = weatherRepository.save(weatherEntity);
        return newWeatherEntity;
    }

    /**
     * Busca en la Base de datos los registros de la ciudad especifica
     * y chequea si esta dentro de los 5 dias y los retorna de lo contrario
     * retorna null 
     * 
     * @param name
     * @return
     */
    public List<WeatherEntity> getFiveDay(String name){
        name = formatName(name);
        List<WeatherEntity> ret = new ArrayList<WeatherEntity>();
        LocalDate current = LocalDate.now();
        List<WeatherEntity> weatherEntities= weatherRepository.findByWeatherTypeAndLocalizedName("D", name);
        for (WeatherEntity weatherEntity : weatherEntities) {
            LocalDate comare = weatherEntity.getWeatherDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(comare.compareTo(current) >= 0){
                ret.add(weatherEntity);
            }
        }
        if (ret.size() < 5){
            ret.clear();
            return ret ;
        }
        return  ret;
    }
    
    /**
     * Busca en la API de accuweather 5 dias de pronostico del tiempo
     * de la ciudad especificada y los guarda en la base de datos
     * 
     * @param name
     * @return
     */
    public List<WeatherEntity> getExternalWeatherbyFiveDay(String name){
        name = formatName(name);
        String key = this.getlocation(name).getKey();

        List<WeatherEntity>  weatherEntities = new ArrayList<WeatherEntity>();

        String uri = fiveDayUrl +  key +"?apikey=" + apiKey;

        try {
            FiveDayDTO externalFiveDay = restTemplate.getForObject(uri, FiveDayDTO.class);
            if (externalFiveDay == null) {
                return null;
            }
            for (DailyForecastsDTO day : externalFiveDay.getDailyForecasts()) {

                WeatherEntity temp = new WeatherEntity();

                temp.setLocalizedName(name);
                temp.setCreateDate(new Date());
                temp.setDayDescriptions(day.getDay().getIconPhrase());
                temp.setNightDescriptions(day.getNight().getIconPhrase());
                temp.setWeatherDate(day.getDate());
                temp.setDayHasPrecipitation(day.getDay().isHasPrecipitation());
                temp.setNightHasPrecipitation(day.getNight().isHasPrecipitation());
                temp.setMaxTemperature(day.getTemperature().getMaximum().getValue());
                temp.setMinTemperature(day.getTemperature().getMinimum().getValue());
                temp.setWeatherType("D");
                temp = this.save(temp);
                weatherEntities.add(temp);
            }
            
        } catch (Exception e) {
            
        }
        return weatherEntities;
    }


    /**Busca en la Base de datos los registros de la ciudad especifica
     * y chequea si esta dentro de las 12 hs y los retorna, de lo contrario 
     * retorna null 
     * 
     * @param name
     * @return
     */
    public List<WeatherEntity> getTwelveHour(String name){
        name = formatName(name);
        List<WeatherEntity> ret = new ArrayList<WeatherEntity>();
        LocalDateTime current = LocalDateTime.now();
        List<WeatherEntity> weatherEntities= weatherRepository.findByWeatherTypeAndLocalizedName("H", name);
        for (WeatherEntity weatherEntity : weatherEntities) {
            LocalDateTime comare = weatherEntity.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            Duration duracion = Duration.between(comare, current);
            if(duracion.toHours() < 1){
                ret.add(weatherEntity);
            }
        }
        return  ret;
    }


    /**
     * Busca en la API de accuweather las ultimas 12 hs de pronostico 
     * del tiempo de la ciudad especificada y los guarda en la base de datos
     * 
     * @param name
     * @return
     */
    public List<WeatherEntity> getExternalWeatherbyTwelveHour(String name){
        name = formatName(name);
        String key = this.getlocation(name).getKey();

        List<WeatherEntity>  weatherEntities = new ArrayList<WeatherEntity>();

        String uri = twelveHourUrl +  key +"?apikey=" + apiKey;

        try {
            List<TwelveHourDTO> externalTwelveHour = Arrays.asList(restTemplate.getForObject(uri, TwelveHourDTO[].class));
            if (externalTwelveHour.isEmpty()) {
                return null;
            }
            for (TwelveHourDTO hour : externalTwelveHour) {

                WeatherEntity temp = new WeatherEntity();

                temp.setLocalizedName(name);
                temp.setCreateDate(new Date());

                if (hour.isDaylight()) {
                    temp.setDayDescriptions(hour.getIconPhrase());
                    temp.setNightDescriptions("");
                    temp.setDayHasPrecipitation(hour.isHasPrecipitation());
                }else{
                    temp.setNightDescriptions(hour.getIconPhrase());
                    temp.setDayDescriptions("");
                    temp.setNightHasPrecipitation(hour.isHasPrecipitation());
                }
                temp.setWeatherDate(new Date(hour.getEpochDateTime()*1000));
                temp.setMaxTemperature(hour.getTemperature().getValue());
                temp.setMinTemperature(hour.getTemperature().getValue());
                temp.setWeatherType("H");
                temp = this.save(temp);
                weatherEntities.add(temp);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return weatherEntities;
    }
    
    /**
     * Formatea el nombre de las ciudades para mantener el estandar
     * que requiere accuweather y la base de datos propia
     * 
     * @param name
     * @return
     */
    private String formatName(String name){

        String[] subStrings = name.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (String subString : subStrings) {
            if (subString.length() > 0) {
                result.append(subString.substring(0, 1).toUpperCase());
                result.append(subString.substring(1));
            }
            result.append(" ");
        }
        
        String finalString = result.toString().trim();
        return finalString;
    }
}
