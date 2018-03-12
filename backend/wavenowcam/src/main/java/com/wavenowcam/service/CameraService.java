package com.wavenowcam.service;

import com.wavenowcam.exceptions.SocketException;
import java.net.MalformedURLException;

/**
 *
 * @author guidocorazza
 */
public interface CameraService {
    
    void refreshAllStaticImages();
    Boolean pingCamera(String uri) throws MalformedURLException, SocketException;
    
}
