package com.wavenowcam.service.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.dtos.BeachDTO;
import com.wavenowcam.exceptions.Base64Exception;
import com.wavenowcam.service.BeachService;
import com.wavenowcam.utils.Base64Util;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author guidocorazza
 */
@Service
public class BeachServiceImpl implements BeachService {

    private static final String PNG = ".png";
    private static final String COVERPHOTOPATH = "-cover-photo";
    private static final Logger LOG = Logger.getLogger(BeachServiceImpl.class);

    @Autowired
    private BeachDAO beachDAO;

    @Override
    public Long saveOrUpdateBeach(BeachDTO beachDto, Boolean updating) {
        try {
            Beach beach = beachDTO2Beach(beachDto, updating);
            if (beach.getCoverPhotoBase64() != null) {
                Base64Util.base64AFile(beach.getCoverPhotoBase64(), beach.getCoverPhotoPath(), PNG);
            }
            return beachDAO.saveBeach(beach);
        } catch (Base64Exception ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteBeach(Long id) {
        // eliminar cover photo y todas las fotos que queden del live
        beachDAO.deleteBeach(id);
    }

    @Override
    public BeachDTO getBeachById(Long id) {
        Beach beach = beachDAO.getBeachById(id);
        try {
            if (beach != null) {
                beach.setCoverPhotoBase64(Base64Util.fileABase64(beach.getCoverPhotoPath(), PNG));
            }
        } catch (Base64Exception ex) {
            LOG.error(ex.getMessage());
        }
        return beach2BeachDTO(beach);
    }

    @Override
    public BeachDTO getBeachByName(String name) {
        Beach beach = this.beachDAO.getBeachByName(name);
        return beach2BeachDTO(beach);
    }
    
    @Override
    public List<BeachDTO> getAll() {
        List<Beach> beaches = this.beachDAO.getAll();
        List<BeachDTO> beachesDTO = new ArrayList<>();
        for(Beach beach: beaches){
            beachesDTO.add(beach2BeachDTO(beach));
        }
        
        return beachesDTO;
    }

    private BeachDTO beach2BeachDTO(Beach beach) {
        BeachDTO beachDto = null;
        if (beach != null) {
            beachDto = new BeachDTO();
            beachDto.setId(beach.getId());
            beachDto.setName(beach.getName());
            beachDto.setCoverPhotoBase64(beach.getCoverPhotoBase64());
        }
        return beachDto;
    }

    private Beach beachDTO2Beach(BeachDTO beachDto, Boolean updatingBeach) {
        Beach beach = new Beach();
        if (updatingBeach) {
            beach.setId(beachDto.getId());
        }
        beach.setName(beachDto.getName());
        beach.setCoverPhotoPath(beachDto.getName() + COVERPHOTOPATH);
        beach.setCoverPhotoBase64(beachDto.getCoverPhotoBase64());

        return beach;
    }

    

}
