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
public class FoodVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @NonNull
    private String foodValue;

    @ElementCollection
    @CollectionTable(name = "VoteValues_PlayerVotes", joinColumns = @JoinColumn(name = "food_vote_id"))
    @MapKeyJoinColumn(name = "player_id")
    @Column(name = "vote_value")
    private Map playerVotes;
}
