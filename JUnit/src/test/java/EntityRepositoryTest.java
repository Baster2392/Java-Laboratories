import org.example.EntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntityRepositoryTest {

    private EntityRepository<String> repository;

    @BeforeEach
    void setUp() {
        repository = new EntityRepository<>();
    }

    @Test
    void saveEntity_Successfully() {
        // Given
        String key = "1";
        String entity = "Entity";

        // When
        repository.save(key, entity);
        Optional<String> result = repository.findById(key);

        // Then
        assertThat(result).isPresent().contains(entity);
    }

    @Test
    void saveEntity_KeyAlreadyExists_ThrowsIllegalArgumentException() {
        // Given
        String key = "1";
        String entity = "Entity";
        repository.save(key, entity);

        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> repository.save(key, entity));
    }

    @Test
    void findById_EntityExists_ReturnsOptionalWithValue() {
        // Given
        String key = "1";
        String entity = "Entity";
        repository.save(key, entity);

        // When
        Optional<String> result = repository.findById(key);

        // Then
        assertThat(result).isPresent().contains(entity);
    }

    @Test
    void findById_EntityDoesNotExist_ReturnsOptionalEmpty() {
        // Given
        String key = "1";

        // When
        Optional<String> result = repository.findById(key);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void deleteEntity_EntityExists_Successfully() {
        // Given
        String key = "1";
        String entity = "Entity";
        repository.save(key, entity);

        // When
        repository.delete(key);
        Optional<String> result = repository.findById(key);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void deleteEntity_EntityDoesNotExist_ThrowsIllegalArgumentException() {
        // Given
        String key = "1";

        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> repository.delete(key));
    }
}
