package com.wavenowcam.service;

import com.wavenowcam.dtos.CarouselBeachDTO;
import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.dtos.SelectedBeachDTO;
import java.util.List;


/**
 *
 * @author guidocorazza
 */
public interface BeachService {
    
    Long saveOrUpdateBeach(EditBeachDTO beach, Boolean updating);
    void deleteBeach(Long id);
    SelectedBeachDTO getBeachById(Long id);
    EditBeachDTO getBeachByName(String name);
    List<CarouselBeachDTO> getAll();
    
}
