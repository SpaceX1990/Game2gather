
package de.szut.game2gather_backend;

import de.szut.game2gather_backend.controller.TagController;
import de.szut.game2gather_backend.dto.TagDTO;
import de.szut.game2gather_backend.service.TagService;
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
class TagControllerTests {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    @Test
    void getAllTags() throws Exception {
        List<TagDTO> tagDTOList = Collections.singletonList(new TagDTO(1, "Adventure"));
        when(tagService.readAll()).thenReturn(tagDTOList);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();

        mockMvc.perform(get("/api/tag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].label").value("Adventure"));

        verify(tagService, times(1)).readAll();
    }
}