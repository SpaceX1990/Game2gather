package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoodVoteOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @NonNull
    private String foodOptionValue;

    @OneToMany(mappedBy = "foodVoteOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerVote> playerVotes = new ArrayList<>();
}