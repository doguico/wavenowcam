package com.wavenowcam.controller;

import com.wavenowcam.dtos.EditBeachDTO;
import com.wavenowcam.exceptions.Base64Exception;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guidocorazza
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void GuardarImg(@RequestBody EditBeachDTO beach) {
        try {
            try {
            Base64 decoder = new Base64();

            byte[] imageByte = decoder.decode(beach.getCoverPhotoBase64());

            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            BufferedImage image = ImageIO.read(bis);
            bis.close();

            ImageIO.write(image, "png", new File("/Users/guidocorazza/Workspace/wavesnowcam/POCS/Test.png"));
        } catch (IOException ex) {
            throw new Base64Exception(Base64Exception.DECODIFICAR);
        }
            

        } catch (Base64Exception ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}
