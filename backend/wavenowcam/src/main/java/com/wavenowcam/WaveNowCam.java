package com.wavenowcam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author guidocorazza
 */
@EnableScheduling
@SpringBootApplication
public class WaveNowCam {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WaveNowCam.class, args);
    }

}
