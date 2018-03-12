package com.wavenowcam.service.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.exceptions.SocketException;
import com.wavenowcam.service.CameraService;
import com.wavenowcam.utils.SocketUtil;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author guidocorazza
 */
@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private BeachDAO beachDAO;

    private static final String PNG = "png";
    private static final String DATE_FORMAT = "HH:mm:ss";
    private static final Logger LOG = LogManager.getLogger(CameraServiceImpl.class);

    @Override
    public void refreshAllStaticImages() {
        List<Beach> beaches = this.beachDAO.getAll();
        for (Beach beach : beaches) {
            try {
                beach.setUrl(new URL(beach.getUri()));
                //TODO: Hacer esto en distintos threads?
                refreshStaticImage(beach);
            } catch (MalformedURLException ex) {
                LOG.error("URI de la playa " + beach.getName() + " mal formada. URI: " + beach.getUri());
            }
        }
    }

    @Override
    public Boolean pingCamera(String uri) throws MalformedURLException, SocketException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void refreshStaticImage(Beach beach) {
        try {
            // TODO: Ver que hacer con el puerto
            int port = 80;
            String host = beach.getUrl().getHost();
            String path = beach.getStaticPhotoPath();
            String endpoint = beach.getUrl().getFile();

            LOG.info("Refrescando imagen de " + beach.getName());

            InputStream is = SocketUtil.sendRequestToSocket(host, port, endpoint);
            SocketUtil.socketResponseToFile(is, path, PNG);
            updateLastUpdate(beach);

        } catch (SocketException ex) {
            LOG.error("Error al conectarse con la camara : " + beach.getName() + " - " + ex.getMessage());
        }
    }

    private void updateLastUpdate(Beach beach) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        beach.setLastUpdate(dateFormat.format(new Date()));
        this.beachDAO.saveBeach(beach);
    }
}
