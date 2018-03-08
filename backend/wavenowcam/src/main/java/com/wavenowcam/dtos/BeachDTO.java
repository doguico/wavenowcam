package com.wavenowcam.dtos;

/**
 *
 * @author guidocorazza
 */
public class BeachDTO {
    private Long id;
    private String name;
    private String coverPhotoBase64;
    
    public BeachDTO() { }

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
