package com.wavenowcam.service;

import com.wavenowcam.dos.Beach;
import com.wavenowcam.dtos.CarouselBeachDTO;
import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.dtos.SelectedBeachDTO;
import java.util.List;


/**
 *
 * @author guidocorazza
 */
public interface BeachService {
    
    public Long saveOrUpdateBeach(EditBeachDTO beach, Boolean updating);
    public void deleteBeach(Long id);
    public SelectedBeachDTO getBeachById(Long id);
    public EditBeachDTO getBeachByName(String name);
    public List<CarouselBeachDTO> getAll();
    
    public void refreshBeachStaticImage(Beach beach);
}
