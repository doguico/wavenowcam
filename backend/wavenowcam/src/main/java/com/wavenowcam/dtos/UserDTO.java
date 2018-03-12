package com.wavenowcam.dtos;

import com.wavenowcam.dos.User;

/**
 *
 * @author guidocorazza
 */
public class UserDTO {
    private String email;
    private String password;

    public UserDTO() {
    }
    
    public UserDTO(User user) {
        this.email = user.getEmail();
        this.email = user.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
