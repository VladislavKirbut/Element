package by.element.elementapp.repository.authentication;

import by.element.elementapp.models.user.AuthenticationData;
import by.element.elementapp.models.user.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
    public Optional<AuthenticationData> getAuthorizationDataByPhoneNumber(String phoneNumber) {
        TypedQuery<AuthenticationData> query = entityManager.createQuery(
                """
                            SELECT AuthenticationData
                            FROM AuthenticationData authenticationData
                            JOIN FETCH authenticationData.users
                            WHERE authenticationData.phoneNumber = :phoneNumber
                            """,
                AuthenticationData.class
        );

        query.setParameter("phoneNumber", phoneNumber);

        return query.getResultStream().findFirst();
    }

    public void createUser(Users user) {
        entityManager.persist(user);
    }
}
