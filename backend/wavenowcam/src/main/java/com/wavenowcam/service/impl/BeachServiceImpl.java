package com.wavenowcam.service.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.dtos.CarouselBeachDTO;
import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.dtos.SelectedBeachDTO;
import com.wavenowcam.exceptions.Base64Exception;
import com.wavenowcam.service.BeachService;
import com.wavenowcam.utils.Base64Util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private static final String GET = "GET";
    private static final String PNG = "png";
    private static final String HOST = "Host";
    private static final String HTTP11 = "HTTP/1.1";
    private static final String COVER_PHOTO_PATH = "-cover-photo";
    private static final String STATIC_PHOTO_PATH = "-static-photo";
    private static final Logger LOG = Logger.getLogger(BeachServiceImpl.class);
    
    @Autowired
    private BeachDAO beachDAO;

    @Override
    public Long saveOrUpdateBeach(EditBeachDTO beachDto, Boolean updating) {
        try {
            Beach beach = editBeachDTO2Beach(beachDto, updating);
            if (beach.getCoverPhotoBase64() != null) {
                Base64Util.base64AFile(beach.getCoverPhotoBase64(), beach.getCoverPhotoPath(), PNG);
                beach.setCoverPhotoBase64(null);
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
    public SelectedBeachDTO getBeachById(Long id) {
        SelectedBeachDTO selectedBeach = null;
        try {
            Beach beach = beachDAO.getBeachById(id);
            if (beach != null) {
                beach.setCoverPhotoBase64(Base64Util.fileABase64(beach.getCoverPhotoPath(), PNG));
                selectedBeach = new SelectedBeachDTO(beach);
            }
        } catch (Base64Exception ex) {
            LOG.error(ex.getMessage());
        }
        
        return selectedBeach;
    }

    @Override
    public EditBeachDTO getBeachByName(String name) {
        Beach beach = this.beachDAO.getBeachByName(name);
        return beach != null ? new EditBeachDTO(beach) : null;
    }

    @Override
    public List<CarouselBeachDTO> getAll() {
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

    @Override
    public void refreshBeachStaticImage(Beach beach) {
        if (beach != null) {
            if (beach.getUri() != null) {
                try {
                    // uri = http://host/endpoint
                    String host = beach.getUri().split("//")[1];
                    String endpoint = beach.getUri().split("//")[2];

                    refreshImage(host, endpoint, beach.getStaticPhotoPath());
                    updateLastUpdate(beach);
                } catch (IOException ex) {
                    LOG.error("Error al conectarse con la camara : " + beach.getName());
                    LOG.error(ex.getMessage());
                }
            }
        }
    }

    private void updateLastUpdate(Beach beach) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        beach.setLastUpdate(dateFormat.format(new Date()));
        this.beachDAO.saveBeach(beach);
    }

    private void refreshImage(String host, String endpoint, String path) throws IOException {
        Socket socket = new Socket(host, 80);

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.print(GET + " " + endpoint + " " + HTTP11 + "\r\n");
        pw.print(HOST + ": " + host + "\r\n\r\n");
        pw.println("");

        InputStream is = socket.getInputStream();
        OutputStream os = new FileOutputStream(path);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
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

        return beach;
    }
}
