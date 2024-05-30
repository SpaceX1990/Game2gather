
package de.szut.game2gather_backend;

import de.szut.game2gather_backend.controller.GenreController;
import de.szut.game2gather_backend.dto.GenreDTO;
import de.szut.game2gather_backend.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class GenreControllerTests {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @Test
    void getAllGenres() throws Exception {
        List<GenreDTO> genreDTOList = Collections.singletonList(new GenreDTO(1, "Action"));
        when(genreService.readAll()).thenReturn(genreDTOList);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();

        mockMvc.perform(get("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(genreService, times(1)).readAll();
    }
}