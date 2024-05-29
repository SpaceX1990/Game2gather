
package de.szut.game2gather_backend;

import de.szut.game2gather_backend.controller.SessionController;
import de.szut.game2gather_backend.dto.*;
import de.szut.game2gather_backend.entity.*;
import de.szut.game2gather_backend.service.SessionService;
import de.szut.game2gather_backend.repository.GameVoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SessionControllerTests {
    Genre genre = new Genre(1,"lable");
    byte[] bytes = new byte[2];

    Tag tag1 = new Tag();
    Tag tag2 = new Tag();
    List<Tag> tags = List.of(tag1, tag2);
    Game voteoption = new Game(1,"title",1,2,tags,genre,bytes);

    PlayerDTO player = new PlayerDTO(1, "Thomas", 1);
    PlayerDTO player1 = new PlayerDTO(2,"William", 3);
    List<PlayerDTO> players = List.of(player, player1);


    Player player3 = new Player();
    Player player4 = new Player();
    private VoteValueEnum VoteValue;
    private VoteValueEnum VoteValue2;
    UserVote userVote = new UserVote(1,player3, VoteValue);
    UserVote userVote2 = new UserVote(2, player4, VoteValue2);
    List<UserVote> userVotes = List.of(userVote, userVote2);

    GameVoteDTO gameVote1 = new GameVoteDTO(1, 1, voteoption, userVotes);
    GameVoteDTO gameVote2 = new GameVoteDTO(2,2, voteoption, userVotes);
    List<GameVoteDTO> gameVotes = List.of(gameVote1, gameVote2);

    LocalDateTime voteOption = LocalDateTime.now();
    DateVoteDTO dateVote1 = new DateVoteDTO(1,1, voteOption, userVotes);
    DateVoteDTO dateVote2 = new DateVoteDTO(2,2, voteOption, userVotes);
    List<DateVoteDTO> dateVotes = List.of(dateVote1, dateVote2);

    FoodVoteDTO foodVote1 = new FoodVoteDTO(1,1, "food", userVotes);
    FoodVoteDTO foodVote2 = new FoodVoteDTO(2,2, "foodie", userVotes);
    List<FoodVoteDTO> foodVotes = List.of(foodVote1, foodVote2);



    @Mock
    private SessionService sessionService;

    @Mock
    private GameVoteRepository gameVoteRepository;

    @InjectMocks
    private SessionController sessionController;

    @BeforeEach
    public void setUp() {


        MockitoAnnotations.openMocks(this);


    }

    @Test
    public void testGetSession() {
        int sessionId = 1;
        Session session = new Session();
        when(sessionService.getById(sessionId)).thenReturn(Optional.of(session));

        Optional<Session> result = sessionController.getSession(sessionId);

        assertTrue(result.isPresent());
        assertEquals(session, result.get());
        verify(sessionService, times(1)).getById(sessionId);
    }

    @Test
    public void testGetAllActiveSessions() {
        List<SessionDTO> sessionDTOs = Arrays.asList(new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes), new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes));
        when(sessionService.getAllActiveSession()).thenReturn(sessionDTOs);

        List<SessionDTO> result = sessionController.getAllActiveSessions();

        assertEquals(sessionDTOs, result);
        verify(sessionService, times(1)).getAllActiveSession();
    }

    @Test
    public void testGetAll() {
        List<SessionDTO> sessionDTOs = Arrays.asList(new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes), new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes));
        when(sessionService.readAll()).thenReturn(sessionDTOs);

        List<SessionDTO> result = sessionController.getAll();

        assertEquals(sessionDTOs, result);
        verify(sessionService, times(1)).readAll();
    }

    @Test
    public void testGetAllPastSessions() {
        List<SessionDTO> sessionDTOs = Arrays.asList(new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes), new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes));
        when(sessionService.getAllPastSession()).thenReturn(sessionDTOs);

        List<SessionDTO> result = sessionController.getAllPastSessions();

        assertEquals(sessionDTOs, result);
        verify(sessionService, times(1)).getAllPastSession();
    }

    @Test
    public void testUpdateSession() {
        SessionDTO sessionDTO = new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes);
        when(sessionService.update(sessionDTO)).thenReturn(sessionDTO);

        SessionDTO result = sessionController.updateSession(sessionDTO);

        assertEquals(sessionDTO, result);
        verify(sessionService, times(1)).update(sessionDTO);
    }

    @Test
    public void testTest() {
        List<GameVote> gameVotes = Arrays.asList(new GameVote(), new GameVote());
        when(gameVoteRepository.findAll()).thenReturn(gameVotes);

        List<GameVote> result = sessionController.test();

        assertEquals(gameVotes, result);
        verify(gameVoteRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteSession() {
        int sessionId = 1;

        sessionController.deleteSession(sessionId);

        verify(sessionService, times(1)).delete(sessionId);
    }

    @Test
    public void testCreateSession() {
        SessionDTO sessionDTO = new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes);
        when(sessionService.create(sessionDTO)).thenReturn(sessionDTO);

        SessionDTO result = sessionController.createSession(sessionDTO);

        assertEquals(sessionDTO, result);
        verify(sessionService, times(1)).create(sessionDTO);
    }
}
