package by.element.app.repository.chat;

import by.element.app.models.folder.Folder;
import by.element.app.models.user.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<Folder> getFolderByUserId(UUID userId) {
        String sql = """
                SELECT folder
                FROM Folder folder
                WHERE folder.user.id = :userId
                """;

        return entityManager.createQuery(sql, Folder.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void createFolder(UUID userId, String folderName) {

        Users user = entityManager.find(Users.class, userId);

        Folder folder = new Folder()
                .setFolderName(folderName)
                        .setUser(user);

        entityManager.persist(folder);
    }
}
