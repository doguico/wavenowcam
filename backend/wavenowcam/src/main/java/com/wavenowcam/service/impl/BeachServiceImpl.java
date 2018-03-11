package com.wavenowcam.service.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.dtos.CarouselBeachDTO;
import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.dtos.SelectedBeachDTO;
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

    private static final String PNG = "png";
    private static final String COVER_PHOTO_PATH = "-cover-photo";
    private static final String STATIC_PHOTO_PATH = "-static-photo";
    private static final Logger LOG = Logger.getLogger(BeachServiceImpl.class);

    @Autowired
    private BeachDAO beachDAO;

    @Override
    public Long saveOrUpdateBeach(EditBeachDTO beachDto, Boolean updating) {
        // TODO: Definir que hacer si no hay cover photo
        try {
            Beach beach = editBeachDTO2Beach(beachDto, updating);
            if (beach.getCoverPhotoBase64() != null) {
                Base64Util.base64AFile(beach.getCoverPhotoBase64(), beach.getCoverPhotoPath(), PNG);
                beach.setCoverPhotoBase64(null);
            }
            // Si cambia el nombre
            return beachDAO.saveBeach(beach);
        } catch (Base64Exception ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteBeach(Long id) {
        // TODO: eliminar cover photo y todas las fotos que queden del live
        beachDAO.deleteBeach(id);
    }

    @Override
    public SelectedBeachDTO getBeachById(Long id) {
        // TODO: Definir que hacer si no hay cover photo
        Beach beach = beachDAO.getBeachById(id);
        try {
            if (beach != null) {
                beach.setCoverPhotoBase64(Base64Util.fileABase64(beach.getCoverPhotoPath(), PNG));
            }
        } catch (Base64Exception ex) {
            LOG.error(ex.getMessage());
        }

        return new SelectedBeachDTO(beach);
    }

    @Override
    public EditBeachDTO getBeachByName(String name) {
        Beach beach = this.beachDAO.getBeachByName(name);
        return beach != null ? new EditBeachDTO(beach) : null;
    }

    @Override
    public List<CarouselBeachDTO> getAll() {
        // TODO: Definir que hacer si no hay cover photo
        List<Beach> beaches = this.beachDAO.getAll();
        List<CarouselBeachDTO> beachesDTO = new ArrayList<>();
        for (Beach beach : beaches) {
            try {
                beach.setCoverPhotoBase64(Base64Util.fileABase64(beach.getCoverPhotoPath(), PNG));
                beachesDTO.add(new CarouselBeachDTO(beach));
            } catch (Base64Exception ex) {
                LOG.error(ex.getMessage());
            }
        }

        return beachesDTO;
    }

    private Beach editBeachDTO2Beach(EditBeachDTO beachDto, Boolean updatingBeach) {
        Beach beach = new Beach();
        if (updatingBeach) {
            beach.setId(beachDto.getId());
        }
        beach.setName(beachDto.getName());
        beach.setCoverPhotoPath("/Users/guidocorazza/Workspace/wavesnowcam/POCS/" + beachDto.getName() + COVER_PHOTO_PATH);
        beach.setCoverPhotoBase64(beachDto.getCoverPhotoBase64());
        beach.setStaticPhotoPath("/Users/guidocorazza/Workspace/wavesnowcam/POCS/" + beach.getName() + STATIC_PHOTO_PATH);
        beach.setUri(beachDto.getUri());

        return beach;
    }
}
