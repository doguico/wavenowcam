package com.wavenowcam.service.impl;

import com.wavenowcam.dao.UserDAO;
import com.wavenowcam.dos.User;
import com.wavenowcam.dtos.UserDTO;
import com.wavenowcam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author guidocorazza
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(UserDTO user) {
        this.userDAO.save(new User(user));
    }

    @Override
    public void delete(UserDTO user) {
        this.userDAO.delete(new User(user));
    } 
    
    @Override
    public UserDTO getUserByEmail(String email) {
        User user = this.userDAO.getUserByEmail(email);
        if(user != null) {
            return new UserDTO(user);
        }
        
        return null;
    }
}
