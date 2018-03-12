package com.wavenowcam.dao;

import com.wavenowcam.dos.User;

/**
 *
 * @author guidocorazza
 */
public interface UserDAO {
    void save(User user);
    void delete(User user);
    User getUserByEmail(String email);
}
