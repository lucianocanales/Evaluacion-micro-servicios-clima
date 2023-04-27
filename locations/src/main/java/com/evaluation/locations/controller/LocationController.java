package com.evaluation.locations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.locations.entity.LocationEntity;
import com.evaluation.locations.service.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    
    /**
     * Retorna toda la lista de locaciones de la base de datos
     * precaucion puede traer todo y generar problemas de carga
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<LocationEntity>> getAll(){
        List<LocationEntity> locationEntities = locationService.getAll();
        if(locationEntities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(locationEntities);
    }

    /**
     * Busca en la base de datos si encuentra una ciudad con el nombre especificado
     * y la retorana de lo contrario va a buscar el registro a la API de accuweather
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public ResponseEntity<LocationEntity> getLocationByName(@PathVariable("name") String name){
       
        List<LocationEntity> locationEntities = locationService.getLocationByName(name);
        if (locationEntities.isEmpty()) {
            LocationEntity externalLocation = locationService.getExternalLocation(name);
            if (externalLocation == null) {
                return ResponseEntity.notFound().build(); 
            }
            return ResponseEntity.ok(externalLocation);
        }
        return ResponseEntity.ok(locationEntities.get(0));
    }

}
