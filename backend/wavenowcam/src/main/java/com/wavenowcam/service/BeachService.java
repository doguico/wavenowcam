package com.wavenowcam.service;

import com.wavenowcam.dtos.BeachDTO;
import java.util.List;


/**
 *
 * @author guidocorazza
 */
public interface BeachService {
    
    public Long saveOrUpdateBeach(BeachDTO beach, Boolean updating);
    public void deleteBeach(Long id);
    public BeachDTO getBeachById(Long id);
    public BeachDTO getBeachByName(String name);
    public List<BeachDTO> getAll();
}
