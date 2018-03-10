package com.wavenowcam.service.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.exceptions.SocketException;
import com.wavenowcam.service.CameraService;
import com.wavenowcam.utils.SocketUtil;
import java.io.IOException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
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
    private static final Logger LOG = Logger.getLogger(CameraServiceImpl.class);

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
        try {
            int port = 80;
            URL url = new URL(uri);            
            int cant=  SocketUtil.sendRequestToSocket(url.getHost(), port, url.getFile()).read(new byte[2048]);
//            return SocketUtil.sendRequestToSocket(url.getHost(), port, url.getFile()).read(new byte[2048]) != -1;
return cant != -1;
        } catch (IOException ex) {
            throw new SocketException(SocketException.READ_ERROR);
        }
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        beach.setLastUpdate(dateFormat.format(new Date()));
        this.beachDAO.saveBeach(beach);
    }
}
