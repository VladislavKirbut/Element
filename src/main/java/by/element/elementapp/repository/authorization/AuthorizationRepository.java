package by.element.elementapp.repository.authorization;

import by.element.elementapp.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class AuthorizationRepository implements AuthorizationPageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUserToDB(Users user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserFromDB(UUID id) {
        Users user = entityManager.getReference(Users.class, id);

        entityManager.remove(user);
        log.info("Removed user with id: " + user.getId());
    }



}
