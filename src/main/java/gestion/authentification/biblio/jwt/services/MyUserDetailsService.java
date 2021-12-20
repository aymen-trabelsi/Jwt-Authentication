package gestion.authentification.biblio.jwt.services;

import gestion.authentification.biblio.application.models.DAOUser;
import gestion.authentification.biblio.application.dto.UserDTO;
import gestion.authentification.biblio.application.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcryptEncoder ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDAO.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username : "+username);
        }

        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

    public DAOUser save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDAO.save(newUser);
    }
}
