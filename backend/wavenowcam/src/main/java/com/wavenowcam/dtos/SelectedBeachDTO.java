package com.wavenowcam.dtos;

import com.wavenowcam.dos.Beach;

/**
 *
 * @author guidocorazza
 */
public class SelectedBeachDTO {
    private String name;
    private String staticPhotoBase64;
    private String lastUpdate;
    
    public SelectedBeachDTO() { }

    public SelectedBeachDTO(Beach beach) {
        this.name = beach.getName();
        this.staticPhotoBase64 = beach.getStaticPhotoBase64();
        this.lastUpdate = beach.getLastUpdate();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaticPhotoBase64() {
        return staticPhotoBase64;
    }

    public void setStaticPhotoBase64(String staticPhotoBase64) {
        this.staticPhotoBase64 = staticPhotoBase64;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
