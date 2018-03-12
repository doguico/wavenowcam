package com.wavenowcam.service;

import com.wavenowcam.dtos.UserDTO;

/**
 *
 * @author guidocorazza
 */
public interface UserService {
    void save(UserDTO user);
    void delete(UserDTO id); 
    UserDTO getUserByEmail(String email);
}
