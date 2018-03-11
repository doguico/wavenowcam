package com.wavenowcam.service;

import com.wavenowcam.exceptions.SocketException;
import java.net.MalformedURLException;

/**
 *
 * @author guidocorazza
 */
public interface CameraService {
    
    public void refreshAllStaticImages();
    public Boolean pingCamera(String uri) throws MalformedURLException, SocketException;
    
}
