

package de.szut.game2gather_backend;

        import de.szut.game2gather_backend.dto.GenreDTO;
        import de.szut.game2gather_backend.entity.Genre;
        import de.szut.game2gather_backend.repository.GenreRepository;
        import de.szut.game2gather_backend.service.GenreService;
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
class GenreServiceTests {

    @Mock
    private GenreRepository repository;

    @InjectMocks
    private GenreService service;

    private Genre genre;
    private GenreDTO genreDTO;

    @BeforeEach
    void setUp() {
        genre = new Genre();
        genre.setId(1);
        genre.setLabel("Action");

        genreDTO = new GenreDTO(5, "Test Genre");
    }

    @Test
    void readAll() {
        when(repository.findAll()).thenReturn(List.of(genre));

        List<GenreDTO> genreDTOList = service.readAll();

        assertEquals(1, genreDTOList.size());
        assertEquals(genre.getLabel(), genreDTOList.get(0).getLabel());
        verify(repository, times(1)).findAll();
    }
}
