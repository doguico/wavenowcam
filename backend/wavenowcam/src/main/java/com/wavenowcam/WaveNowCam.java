package com.wavenowcam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author guidocorazza
 */
@EnableScheduling
@SpringBootApplication
public class WaveNowCam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        SpringApplication.run(WaveNowCam.class, args);
    }

}
