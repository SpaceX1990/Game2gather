package de.szut.game2gather_backend;

import de.szut.game2gather_backend.controller.GameController;
import de.szut.game2gather_backend.dto.GameDTO;
import de.szut.game2gather_backend.entity.Game;
import de.szut.game2gather_backend.entity.Genre;
import de.szut.game2gather_backend.entity.Tag;
import de.szut.game2gather_backend.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(GameController.class)
@ExtendWith(MockitoExtension.class)
class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private Game game;
    private GameDTO gameDTO;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setId(1);
        game.setTitle("Test Game");

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
    void getAll() throws Exception {
        when(gameService.readAll()).thenReturn(List.of(gameDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/game")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Game"));

        verify(gameService, times(1)).readAll();
    }

    @Test
    void get() throws Exception {
        when(gameService.read(1)).thenReturn(Optional.of(game));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/game/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Game"));

        verify(gameService, times(1)).read(1);
    }

    @Test
    void deleteGame() throws Exception {
        doNothing().when(gameService).delete(1);

        mockMvc.perform(delete("/api/game/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(gameService, times(1)).delete(1);
    }

    @Test
    void create() throws Exception {
        when(gameService.create(any(GameDTO.class))).thenReturn(gameDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String gameJson = objectMapper.writeValueAsString(gameDTO);

        mockMvc.perform(post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Game"));

        verify(gameService, times(1)).create(any(GameDTO.class));
    }

    @Test
    void update() throws Exception {
        when(gameService.update(any(Game.class))).thenReturn(game);

        mockMvc.perform(put("/api/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(gameService, times(1)).update(any(Game.class));
    }
}