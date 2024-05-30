package de.szut.game2gather_backend;

import de.szut.game2gather_backend.dto.*;
import de.szut.game2gather_backend.entity.*;
import de.szut.game2gather_backend.repository.SessionRepository;
import de.szut.game2gather_backend.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SessionServiceTests {

    Genre genre = new Genre(1, "label");
    byte[] bytes = new byte[2];

    Tag tag1 = new Tag();
    Tag tag2 = new Tag();
    List<Tag> tags = List.of(tag1, tag2);
    Game voteOption = new Game(1, "title", 1, 2, tags, genre, bytes);

    PlayerDTO player = new PlayerDTO(1, "Thomas", 1);
    PlayerDTO player1 = new PlayerDTO(2, "William", 1);
    List<PlayerDTO> players = List.of(player, player1);

    Player player3 = new Player();
    Player player4 = new Player();
    private VoteValueEnum voteValue;
    private VoteValueEnum voteValue2;
    UserVote userVote = new UserVote(1, player3, voteValue);
    UserVote userVote2 = new UserVote(2, player4, voteValue2);
    List<UserVote> userVotes = List.of(userVote, userVote2);

    GameVoteDTO gameVote1 = new GameVoteDTO(1, 1, voteOption, userVotes);
    GameVoteDTO gameVote2 = new GameVoteDTO(2, 2, voteOption, userVotes);
    List<GameVoteDTO> gameVotes = List.of(gameVote1, gameVote2);

    LocalDateTime voteOptionTime = LocalDateTime.now();
    DateVoteDTO dateVote1 = new DateVoteDTO(1, 1, voteOptionTime, userVotes);
    DateVoteDTO dateVote2 = new DateVoteDTO(2, 2, voteOptionTime, userVotes);
    List<DateVoteDTO> dateVotes = List.of(dateVote1, dateVote2);

    FoodVoteDTO foodVote1 = new FoodVoteDTO(1, 1, "food", userVotes);
    FoodVoteDTO foodVote2 = new FoodVoteDTO(2, 2, "foodie", userVotes);
    List<FoodVoteDTO> foodVotes = List.of(foodVote1, foodVote2);

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private GameVoteService gameVoteService;

    @Mock
    private FoodVoteService foodVoteService;

    @Mock
    private DateVoteService dateVoteService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private SessionService sessionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllActiveSession() {
        List<Session> sessions = Arrays.asList(new Session(), new Session());
        when(sessionRepository.findByActiveTrue()).thenReturn(sessions);
        List<SessionDTO> expected = sessions.stream().map(SessionDTO::fromModel).toList();

        List<SessionDTO> result = sessionService.getAllActiveSession();

        assertEquals(expected, result);
        verify(sessionRepository, times(1)).findByActiveTrue();
    }

    @Test
    public void testGetAllPastSession() {
        List<Session> sessions = Arrays.asList(new Session(), new Session());
        when(sessionRepository.findByActiveFalse()).thenReturn(sessions);
        List<SessionDTO> expected = sessions.stream().map(SessionDTO::fromModel).toList();

        List<SessionDTO> result = sessionService.getAllPastSession();

        assertEquals(expected, result);
        verify(sessionRepository, times(1)).findByActiveFalse();
    }

    @Test
    public void testReadAll() {
        List<Session> sessions = Arrays.asList(new Session(), new Session());
        when(sessionRepository.findAll()).thenReturn(sessions);
        List<SessionDTO> expected = sessions.stream().map(SessionDTO::fromModel).toList();

        List<SessionDTO> result = sessionService.readAll();

        assertEquals(expected, result);
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        int sessionId = 1;

        sessionService.delete(sessionId);

        verify(sessionRepository, times(1)).deleteById(sessionId);
    }

    @Test
    public void testGetById() {
        int sessionId = 1;
        Session session = new Session();
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        Optional<Session> result = sessionService.getById(sessionId);

        assertEquals(Optional.of(session), result);
        verify(sessionRepository, times(1)).findById(sessionId);
    }

    @Test
    public void testCreate() {
        // Setup initial data
        SessionDTO sessionDTO = new SessionDTO(1, "sessiontitle", true, null, 5, 2, players, gameVotes, dateVotes, foodVotes);
        Session session = sessionDTO.toModel();
        session.setId(1);

        // Mock repository save to return the same session with generated link
        when(sessionRepository.save(any(Session.class))).thenAnswer(invocation -> {
            Session savedSession = invocation.getArgument(0);
            savedSession.setSessionVoteLink("http://game2gather.com/vote/" + UUID.randomUUID().toString());
            return savedSession;
        });

        // Call the service method
        SessionDTO result = sessionService.create(sessionDTO);

        // Capture the generated link for comparison
        String generatedLink = result.getSessionVoteLink();
        assertTrue(generatedLink.startsWith("http://game2gather.com/vote/"));
        assertEquals(64, generatedLink.length()); // Check for the correct length of the link

        // Create expected DTO with the dynamically generated link
        SessionDTO expectedSessionDTO = new SessionDTO(1, "sessiontitle", true, generatedLink, 5, 2, players, gameVotes, dateVotes, foodVotes);

        // Verify all fields match
        assertEquals(expectedSessionDTO.getId(), result.getId());
        assertEquals(expectedSessionDTO.getSessionTitle(), result.getSessionTitle());
        assertEquals(expectedSessionDTO.getSessionVoteLink(), result.getSessionVoteLink());
        assertEquals(expectedSessionDTO.getMaxPlayer(), result.getMaxPlayer());
        assertEquals(expectedSessionDTO.getUserId(), result.getUserId());
        //test if same playerObjects in player list have same values
        assertIterableEquals(expectedSessionDTO.getPlayers().stream().map(PlayerDTO::getId).toList(), result.getPlayers().stream().map(PlayerDTO::getId).toList());
        assertIterableEquals(expectedSessionDTO.getPlayers().stream().map(PlayerDTO::getUsername).toList(), result.getPlayers().stream().map(PlayerDTO::getUsername).toList());
        assertIterableEquals(expectedSessionDTO.getPlayers().stream().map(PlayerDTO::getSession_id).toList(), result.getPlayers().stream().map(PlayerDTO::getSession_id).toList());
        //test if same FoodVoteObjects in foodVote list have same values
        assertIterableEquals(expectedSessionDTO.getFoodVotes().stream().map(FoodVoteDTO::getId).toList(), result.getFoodVotes().stream().map(FoodVoteDTO::getId).toList());
        assertIterableEquals(expectedSessionDTO.getFoodVotes().stream().map(FoodVoteDTO::getVoteoption).toList(), result.getFoodVotes().stream().map(FoodVoteDTO::getVoteoption).toList());
        //test if same GameVoteObjects in gameVote list have same values
        assertIterableEquals(expectedSessionDTO.getGameVotes().stream().map(GameVoteDTO::getVoteoption).toList(), result.getGameVotes().stream().map(GameVoteDTO::getVoteoption).toList());
        assertIterableEquals(expectedSessionDTO.getGameVotes().stream().map(GameVoteDTO::getId).toList(), result.getGameVotes().stream().map(GameVoteDTO::getId).toList());
        //test if same DateVoteObjects in dateVote list have same values
        assertIterableEquals(expectedSessionDTO.getDateVotes().stream().map(DateVoteDTO::getVoteoption).toList(), result.getDateVotes().stream().map(DateVoteDTO::getVoteoption).toList());
        assertIterableEquals(expectedSessionDTO.getDateVotes().stream().map(DateVoteDTO::getId).toList(), result.getDateVotes().stream().map(DateVoteDTO::getId).toList());

        // Verify interactions with mocks
        verify(sessionRepository, times(1)).save(any(Session.class));
        //vertify voteSaves are called once aswell
        verify(foodVoteService, times(1)).saveVotesForSessionID(any(), anyInt());
        verify(gameVoteService, times(1)).saveVotesForSessionID(any(), anyInt());
        verify(dateVoteService, times(1)).saveVotesForSessionID(any(), anyInt());
    }


    @Test
    public void testUpdate() {
        // Define initial and updated SessionDTO objects
        SessionDTO initialSessionDTO = new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes);
        SessionDTO updatedSessionDTO = new SessionDTO(1, "updatedTitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes);

        // Define initial and updated Session objects
        Session initialSession = initialSessionDTO.toModel();
        Session updatedSession = updatedSessionDTO.toModel();
        /*updatedSession.setId(1);*/

        // Mock repository behavior
        when(sessionRepository.findById(1)).thenReturn(Optional.of(initialSession));
        when(sessionRepository.save(any(Session.class))).thenReturn(updatedSession);

        // Call the update method
        SessionDTO result = sessionService.update(updatedSessionDTO);

        // Print out expected and actual SessionDTO objects for comparison
        System.out.println("Expected SessionDTO: " + updatedSessionDTO.toString());
        System.out.println("Actual SessionDTO: " + result.toString());

        // Verify all fields match
        assertEquals(updatedSessionDTO.getId(), result.getId());
        assertEquals(updatedSessionDTO.getSessionTitle(), result.getSessionTitle());
        assertEquals(updatedSessionDTO.getSessionVoteLink(), result.getSessionVoteLink());
        assertEquals(updatedSessionDTO.getMaxPlayer(), result.getMaxPlayer());
        assertEquals(updatedSessionDTO.getUserId(), result.getUserId());
        //test if same playerObjects in player list have same values
        assertIterableEquals(updatedSessionDTO.getPlayers().stream().map(PlayerDTO::getId).toList(), result.getPlayers().stream().map(PlayerDTO::getId).toList());
        assertIterableEquals(updatedSessionDTO.getPlayers().stream().map(PlayerDTO::getUsername).toList(), result.getPlayers().stream().map(PlayerDTO::getUsername).toList());
        assertIterableEquals(updatedSessionDTO.getPlayers().stream().map(PlayerDTO::getSession_id).toList(), result.getPlayers().stream().map(PlayerDTO::getSession_id).toList());
        //test if same FoodVoteObjects in foodVote list have same values
        assertIterableEquals(updatedSessionDTO.getFoodVotes().stream().map(FoodVoteDTO::getId).toList(), result.getFoodVotes().stream().map(FoodVoteDTO::getId).toList());
        assertIterableEquals(updatedSessionDTO.getFoodVotes().stream().map(FoodVoteDTO::getVoteoption).toList(), result.getFoodVotes().stream().map(FoodVoteDTO::getVoteoption).toList());
        //test if same GameVoteObjects in gameVote list have same values
        assertIterableEquals(updatedSessionDTO.getGameVotes().stream().map(GameVoteDTO::getVoteoption).toList(), result.getGameVotes().stream().map(GameVoteDTO::getVoteoption).toList());
        assertIterableEquals(updatedSessionDTO.getGameVotes().stream().map(GameVoteDTO::getId).toList(), result.getGameVotes().stream().map(GameVoteDTO::getId).toList());
        //test if same DateVoteObjects in dateVote list have same values
        assertIterableEquals(updatedSessionDTO.getDateVotes().stream().map(DateVoteDTO::getVoteoption).toList(), result.getDateVotes().stream().map(DateVoteDTO::getVoteoption).toList());
        assertIterableEquals(updatedSessionDTO.getDateVotes().stream().map(DateVoteDTO::getId).toList(), result.getDateVotes().stream().map(DateVoteDTO::getId).toList());


        // Verify interactions with mocks
        verify(sessionRepository, times(1)).findById(1);
        verify(sessionRepository, times(1)).save(any(Session.class));
    }


    @Test
    public void testUpdateSessionNotFound() {
        SessionDTO sessionDTO = new SessionDTO(1, "sessiontitle", true, "link", 5, 2, players, gameVotes, dateVotes, foodVotes);
        when(sessionRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            sessionService.update(sessionDTO);
        });

        verify(sessionRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testGenerateRandomLink() {
        String link = sessionService.generateRandomLink();

        assertTrue(link.startsWith("http://game2gather.com/vote/"));
        assertEquals(64, link.length()); // "http://game2gather.com/vote/" + UUID length
    }
}
