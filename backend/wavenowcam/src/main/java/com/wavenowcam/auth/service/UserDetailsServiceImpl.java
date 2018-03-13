package com.wavenowcam.auth.service;

import com.wavenowcam.dao.UserDAO;

import static java.util.Collections.emptyList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author guidocorazza
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    
    private UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.wavenowcam.dos.User applicationUser = userDAO.getUserByEmail(username);
        
        if (applicationUser == null) {
            throw new UsernameNotFoundException("User " + username + " not registerd");
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }    
}
