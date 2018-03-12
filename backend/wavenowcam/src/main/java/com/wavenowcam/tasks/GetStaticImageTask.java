package com.wavenowcam.tasks;

import com.wavenowcam.service.CameraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author guidocorazza
 */
@Component
public class GetStaticImageTask {

    @Autowired
    private CameraService cameraService;
    private static final Logger LOG = LogManager.getLogger(GetStaticImageTask.class);

    @Scheduled(fixedRate = 30000)
    public void refreshAllStaticImages() {
        LOG.info("INICIO : Ejecutando tarea de refresco de imagen estatica");
        this.cameraService.refreshAllStaticImages();
        LOG.info("FINAL : Ejecutando tarea de refresco de imagen estatica");
    }
}
