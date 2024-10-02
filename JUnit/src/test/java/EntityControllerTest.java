import org.example.EntityController;
import org.example.EntityRepository;
import org.example.model.Mage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class EntityControllerTest {
    @Test
    void save_entity_Successfully_returnsDone() {
        // Given
        Mage mage = mock(Mage.class);
        EntityRepository<Mage> repository = mock(EntityRepository.class);
        EntityController<Mage> controller = new EntityController<>(repository);

        // When
        String result = controller.saveEntity("key", mage);

        // Then
        Assertions.assertEquals("done", result);
    }

    @Test
    void save_entity_KeyAlreadyExists_returnsBadRequest() {
        // Given
        EntityRepository repository = mock(EntityRepository.class);
        doThrow(new IllegalArgumentException())
                .when(repository)
                .save(anyString(), any());
        EntityController controller = new EntityController(repository);

        // When
        String result = controller.saveEntity("key", new Object());

        // Then
        Assertions.assertEquals("bad request", result);
    }

    @Test
    void delete_entity_Successfully_returnsDone() {
        // Given
        EntityRepository repository = mock(EntityRepository.class);
        EntityController controller = new EntityController(repository);

        // When
        String result = controller.deleteEntity("key");

        // Then
        Assertions.assertEquals("done", result);
    }

    @Test
    void save_entity_EntityNotFound_returnsNotFound() {
        // Given
        EntityRepository repository = mock(EntityRepository.class);
        doThrow(new IllegalArgumentException())
                .when(repository)
                .delete(anyString());
        EntityController controller = new EntityController(repository);

        // When
        String result = controller.deleteEntity("key");

        // Then
        Assertions.assertEquals("not found", result);
    }

    @Test
    void findById_Successfully_returnsObject() {
        // Given
        Mage mage = mock(Mage.class);
        when(mage.toString()).thenReturn("MockObj");
        EntityRepository<Mage> repository = mock(EntityRepository.class);
        when(repository.findById(any())).thenReturn(Optional.of(mage));
        EntityController<Mage> controller = new EntityController<>(repository);

        // When
        String result = controller.findEntity("key");

        // Then
        Assertions.assertEquals(result, mage.toString());
    }

    @Test
    void findById_NotFound_returnsNotFound() {
        // Given
        Mage mage = mock(Mage.class);
        EntityRepository<Mage> repository = mock(EntityRepository.class);
        when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
        EntityController<Mage> controller = new EntityController<>(repository);

        // When
        String result = controller.findEntity("key");

        // Then
        Assertions.assertEquals(result, "not found");
    }
}
