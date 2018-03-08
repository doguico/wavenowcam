package com.wavenowcam.utils;

import com.wavenowcam.exceptions.Base64Exception;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author guidocorazza
 */
public class Base64Util {

    public static void base64AFile(String base64, String path, String type) throws Base64Exception {
        try {
        Base64 decoder = new Base64();

        byte[] imageByte = decoder.decode(base64);

        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        ImageIO.write(image, type, new File(path + "." + type));
        }
        catch(IOException ex) {
            throw new Base64Exception(Base64Exception.DECODIFICAR);
        }
    }

     public static String fileABase64(String path, String type) throws Base64Exception {
        Base64 encoder = new Base64();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            BufferedImage img = ImageIO.read(new File(path + "." + type));
            ImageIO.write(img, type, bos);
            byte[] imageBytes = bos.toByteArray();
            String imageString = encoder.encodeToString(imageBytes);
            bos.close();

            return imageString;
        }
        catch (IOException e) {
            throw new Base64Exception(Base64Exception.CODIFICAR);
        }
    }
}

 

