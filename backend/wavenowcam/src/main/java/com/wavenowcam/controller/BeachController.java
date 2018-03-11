package com.wavenowcam.controller;

import com.wavenowcam.dtos.CarouselBeachDTO;
import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.dtos.SelectedBeachDTO;
import com.wavenowcam.service.BeachService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guidocorazza
 */
@RestController
@RequestMapping(value = "/beaches")
public class BeachController {

    private static final Logger LOG = Logger.getLogger(BeachController.class);

    @Autowired
    private BeachService beachService;
    
    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity saveBeach(@RequestBody EditBeachDTO beach) {
        Boolean updating = true;
        
        if (beach.getName() == null || beach.getName().trim().isEmpty()) {
            return ResponseEntity.ok("El valor del nombre de la playa no puede ser vacio");
        } else {
            if (newBeach(beach)) {
                if (beachNameIsTaken(beach.getName())) {
                    return ResponseEntity.ok("Ya existe una playa con el nombre " + beach.getName());
                }
                LOG.info("Guardando una nueva playa con los datos: " + beach.toString());
                updating = false;
            } else {
                if (beachNameIsTakenByDifferentBeach(beach)) {
                    return ResponseEntity.ok("Ya existe una playa con el nombre " + beach.getName());
                }
                LOG.info("Actualizando los datos de la playa con los datos: " + beach.toString());
            }

            Long newId = beachService.saveOrUpdateBeach(beach, updating);
            return ResponseEntity.ok(newId);
        }
    }

    //TODO: Eliminar tmb todas las fotos y cover photos
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteBeach(@PathVariable("id") Long id) {
        LOG.info("Se elimina la playa con id: " + id);
        beachService.deleteBeach(id);
    }

    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getBeach(@PathVariable("id") Long id) {
        LOG.info("Se consulta por la playa con id: " + id);
        SelectedBeachDTO beach = beachService.getBeachById(id);
        if (beach == null) {
            return ResponseEntity.ok("No existe una playa con id: " + id);
        }
        else {
            return new ResponseEntity(beach, HttpStatus.OK);
        }
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<CarouselBeachDTO> getAllBeaches() {
        LOG.info("Se obtendran todas las playas registradas");
        return this.beachService.getAll();
    }
    
    private Boolean newBeach(EditBeachDTO beach) {
        return beach.getId() == null;
    }

    private Boolean beachNameIsTaken(String name) {
        return this.beachService.getBeachByName(name) != null;
    }

    private Boolean beachNameIsTakenByDifferentBeach(EditBeachDTO beach) {
        EditBeachDTO oldBeach = this.beachService.getBeachByName(beach.getName());
        return oldBeach != null && !oldBeach.getId().equals(beach.getId());
    }
}
