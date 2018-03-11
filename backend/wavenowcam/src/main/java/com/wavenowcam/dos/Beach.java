package com.wavenowcam.dos;

import java.io.Serializable;
import java.net.URL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

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
    private String uri;
    private String coverPhotoPath;
    private String staticPhotoPath;
    private String lastUpdate;
    
    @Transient
    private String coverPhotoBase64;
    @Transient
    private String staticPhotoBase64;
    @Transient
    private URL url;
    
    private static final Logger LOG = Logger.getLogger(Beach.class);

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    public String getStaticPhotoPath() {
        return staticPhotoPath;
    }

    public void setStaticPhotoPath(String staticPhotoPath) {
        this.staticPhotoPath = staticPhotoPath;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCoverPhotoBase64() {
        return coverPhotoBase64;
    }

    public void setCoverPhotoBase64(String coverPhotoBase64) {
        this.coverPhotoBase64 = coverPhotoBase64;
    }

    public String getStaticPhotoBase64() {
        return staticPhotoBase64;
    }

    public void setStaticPhotoBase64(String staticPhotoBase64) {
        this.staticPhotoBase64 = staticPhotoBase64;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

   
    
    @Override
    public String toString() {
        return "Name: " + this.getName();
    }
    
}
