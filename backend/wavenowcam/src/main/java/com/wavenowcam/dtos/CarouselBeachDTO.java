package com.wavenowcam.dtos;

import com.wavenowcam.dos.Beach;

/**
 *
 * @author guidocorazza
 */
public class CarouselBeachDTO {
    private Long id;
    private String name;
    private String coverPhotoBase64;
    
    public CarouselBeachDTO() { }

    public CarouselBeachDTO(Beach beach) {
        this.id = beach.getId();
        this.name = beach.getName();
        this.coverPhotoBase64 = beach.getCoverPhotoBase64();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPhotoBase64() {
        return coverPhotoBase64;
    }

    public void setCoverPhotoBase64(String coverPhotoBase64) {
        this.coverPhotoBase64 = coverPhotoBase64;
    }
    
    @Override
    public String toString() {
        return "id : " + id + " - name: " + name;
    }
}
