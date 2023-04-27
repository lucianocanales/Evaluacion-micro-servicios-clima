package com.evaluation.locations.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evaluation.locations.dto.LocationDTO;
import com.evaluation.locations.entity.LocationEntity;
import com.evaluation.locations.repository.LocationRepository;


@Service
public class LocationService {
    
    @Value("${spring.external.service.location-url}")
    private String locationUrl;

    @Value("${spring.external.service.api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocationRepository locationRepository;

    /**
     * Retorna toda la lista de locaciones de la base de datos
     * precaucion puede traer todo y generar problemas de carga
     * 
     * @return
     */
    public List<LocationEntity> getAll(){
        return locationRepository.findAll();
    }

    /**
     * Retorna un solo registro de la base de datos a parti de un id
     * 
     * @param id
     * @return
     */
    public LocationEntity getLocationById(int id){
        return locationRepository.findById(id).orElseGet(null);
    }

    /**
     * Retorna una lista de registros de la base de datos a parti de un nombre
     * 
     * @param name
     * @return
     */
    public List<LocationEntity> getLocationByName(String name){
        name = formatName(name,true);
        return locationRepository.findByLocalizedName(name);
    }

    /**
     * Sirve para guarad los datos en la base de datos
     * 
     * @param locationEntity
     * @return
     */
    private LocationEntity save(LocationEntity locationEntity){
        LocationEntity newLocationEntity = locationRepository.save(locationEntity);
        return newLocationEntity;
    }

    /**
     * Busca en la API de accuweather la ciudad especificada 
     * y las guarda en la base de datos
     * 
     * @param name
     * @return
     */
    public LocationEntity getExternalLocation(String name){
        name = formatName(name,false);
        LocationEntity localEntity = new LocationEntity();
        String uri = locationUrl + "?apikey=" + apiKey + "&q=" + name;
        try {
            List<LocationDTO> externalLocation = Arrays.asList(restTemplate.getForObject(uri, LocationDTO[].class));
            if (externalLocation.isEmpty()) {
                return null;
            }
            

            localEntity.setKey(externalLocation.get(0).getKey());
            localEntity.setLocalizedName(externalLocation.get(0).getLocalizedName());
            localEntity = this.save(localEntity);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return localEntity;
    }


    /**
     * Formatea el nombre de las ciudades para mantener el estandar
     * que requiere accuweather y la base de datos propia
     * 
     * @param name
     * @param isLocal
     * @return
     */
    private String formatName(String name, boolean isLocal){

        String[] subStrings = name.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (String subString : subStrings) {
            if (subString.length() > 0) {
                result.append(subString.substring(0, 1).toUpperCase());
                result.append(subString.substring(1));
            }
            result.append(" ");
        }
        
        String finalString =(isLocal) ? result.toString().trim() : result.toString().trim().replace(" ", "");
        return finalString;
    }
}
