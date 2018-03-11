package com.wavenowcam.dtos;

import com.wavenowcam.dos.Beach;

/**
 *
 * @author guidocorazza
 */
public class EditBeachDTO {

    private Long id;
    private String name;
    private String coverPhotoBase64;
    private String uri;
    
    public EditBeachDTO() { }

    public EditBeachDTO(Beach beach) {
        this.id = beach.getId();
        this.name = beach.getName();
        this.coverPhotoBase64 = beach.getCoverPhotoBase64();
        this.uri = beach.getUri();
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    

}
