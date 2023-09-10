package by.element.elementapp.repository.authentication;

import by.element.elementapp.models.user.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class UserAuthenticationRepository implements AuthenticationRepository {

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

    @Override
    public Optional<Users> getUserByPhoneNumber(String phoneNumber) {
         return entityManager.createQuery(
                """
                            SELECT user
                            FROM Users user
                            LEFT JOIN FETCH user.authenticationData
                            WHERE user.authenticationData.phoneNumber = :phoneNumber
                            """,
                Users.class
        ).setParameter("phoneNumber", phoneNumber).getResultStream().findFirst();
    }

    @Override
    public void createUser(Users user) {
        entityManager.persist(user);
    }
}
