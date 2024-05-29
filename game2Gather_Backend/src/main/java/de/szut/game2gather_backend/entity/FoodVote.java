package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Foodvote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FoodVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String voteoption;

    @OneToMany
    @JoinTable(
            name = "foodvote_votes",
            joinColumns = @JoinColumn(name = "foodvote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    List<UserVote> userVotes;

    @JoinColumn(name = "session_id")
    private int session_id;
}
