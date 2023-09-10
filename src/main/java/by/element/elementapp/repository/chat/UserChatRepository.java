package by.element.elementapp.repository.chat;

import by.element.elementapp.models.user.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserChatRepository implements ChatRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Users> getUserInfo(UUID userId) {
        return entityManager.createQuery(
                """
                            SELECT user
                            FROM Users user
                            LEFT JOIN FETCH user.authenticationData
                            WHERE user.id = :userId
                            """,
                Users.class
        ).setParameter("userId", userId).getResultStream().findFirst();
    }
}
