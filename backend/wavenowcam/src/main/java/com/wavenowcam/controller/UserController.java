package com.wavenowcam.controller;

import com.wavenowcam.dtos.UserDTO;
import com.wavenowcam.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guidocorazza
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
    
    @Autowired
    private UserService userService;
        
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody(required = true) UserDTO user) {
        if (parametersAreNotNull(user)) {
            if(emailIsAvailable(user.getEmail())) {
               this.userService.save(user); 
               LOG.info("Creando usuario con email " + user.getEmail());
               return ResponseEntity.ok("Usuario creado con éxito");
            }
            else {
                return ResponseEntity.ok("Ya se ha registrado un usuario con " + user.getEmail());
            }     
        }
        else {
            return ResponseEntity.ok("Se debe especificar email y contraseña");
        }
    }
    
    @RequestMapping(value = "delete/{email}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String email) {
        UserDTO user = this.userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.ok("No existe el usuario registrado con el email " + email);
        }
        else {
            this.userService.delete(user);
            LOG.info("Eliminando usuario con email " + email);
            return ResponseEntity.ok("Se ha eliminado el usuario con éxito");
        }
    }

    private boolean emailIsAvailable(String email) {
        return this.userService.getUserByEmail(email) == null;
    }

    private boolean parametersAreNotNull(UserDTO user) {
        return user != null && user.getEmail() != null & user.getPassword() != null;
    }
}
