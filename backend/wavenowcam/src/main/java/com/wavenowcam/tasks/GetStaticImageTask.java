package com.wavenowcam.tasks;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.service.BeachService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author guidocorazza
 */
@Component
public class GetStaticImageTask {
    
    private static final Logger LOG = Logger.getLogger(GetStaticImageTask.class);
    
    @Autowired
    private BeachDAO beachDao;
    
    @Autowired
    private BeachService beachService;
    
    @Scheduled(fixedRate = 300000)
    public void RefreshBeachStaticImage() {
        List<Beach> beaches = this.beachDao.getAll();
        for(Beach beach: beaches) {
            this.beachService.refreshBeachStaticImage(beach);
        }
    }
}
