package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.mapping.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @NonNull
    private Game gameValue;

    @ElementCollection
    @CollectionTable(name = "VoteValues_PlayerVotes", joinColumns = @JoinColumn(name = "game_vote_id"))
    @MapKeyJoinColumn(name = "player_id")
    @Column(name = "vote_value")
    private Map playerVotes;
}
