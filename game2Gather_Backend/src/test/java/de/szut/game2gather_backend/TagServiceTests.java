
package de.szut.game2gather_backend;

        import de.szut.game2gather_backend.dto.TagDTO;
        import de.szut.game2gather_backend.entity.Tag;
        import de.szut.game2gather_backend.repository.TagRepository;
        import de.szut.game2gather_backend.service.TagService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTests {

    @Mock
    private TagRepository repository;

    @InjectMocks
    private TagService service;

    private Tag tag;
    private TagDTO tagDTO;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1);
        tag.setLabel("Adventure");

        tagDTO = new TagDTO(1, "Test Tag");
    }

    @Test
    void readAll() {
        when(repository.findAll()).thenReturn(List.of(tag));

        List<TagDTO> tagDTOList = service.readAll();

        assertEquals(1, tagDTOList.size());
        assertEquals(tag.getLabel(), tagDTOList.get(0).getLabel());
        verify(repository, times(1)).findAll();
    }
}
