package gestion.authentification.biblio.application.dao;

import gestion.authentification.biblio.application.models.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<DAOUser, Integer> {
    public DAOUser findByUsername(String username);
}
