package de.szut.game2gather_backend;

import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.repository.GameRepository;
import de.szut.game2gather_backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTests {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameService service;

    private Game game;
    private GameDTO gameDTO;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setId(1);
        game.setTitle("Test Game");
        game.setMinPlayer(1);
        game.setMaxPlayer(7);

        Genre genre = new Genre();
        genre.setLabel("Action");

        Tag tag1 = new Tag();
        tag1.setLabel("Adventure");
        Tag tag2 = new Tag();
        tag2.setLabel("Multiplayer");

        List<Tag> tags = List.of(tag1, tag2);

        gameDTO = new GameDTO(1, "Test Game", 2023, 5, tags, genre);
    }

    @Test
    void readAll() {
        when(repository.findAll()).thenReturn(List.of(game));

        List<GameDTO> gameDTOList = service.readAll();

        assertEquals(1, gameDTOList.size());
        assertEquals(game.getTitle(), gameDTOList.get(0).getTitle());
        verify(repository, times(1)).findAll();
    }

    @Test
    void delete() {
        int id = 1;
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void create() {
        when(repository.save(any(Game.class))).thenReturn(game);

        GameDTO createdGameDTO = service.create(gameDTO);

        assertEquals(game.getTitle(), createdGameDTO.getTitle());
        verify(repository, times(1)).save(any(Game.class));
    }

    @Test
    void read() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(game));

        Optional<Game> foundGame = service.read(id);

        assertTrue(foundGame.isPresent());
        assertEquals(game.getTitle(), foundGame.get().getTitle());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void update() {
        when(repository.save(game)).thenReturn(game);

        Game updatedGame = service.update(game);

        assertEquals(game.getTitle(), updatedGame.getTitle());
        verify(repository, times(1)).save(game);
    }
}
