package com.wavenowcam.dos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
/**
 *
 * @author guidocorazza
 */
@Entity
public class Beach implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String coverPhotoPath;
    private String coverPhotoBase64;
    
    public Beach() { }

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

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    public String getCoverPhotoBase64() {
        return coverPhotoBase64;
    }

    public void setCoverPhotoBase64(String coverPhotoBase64) {
        this.coverPhotoBase64 = coverPhotoBase64;
    }
    
    @Override
    public String toString() {
        return "Name: " + this.getName();
    }
    
}
