package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.mapping.Map;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @NonNull
    private Date dateValue;

    @ElementCollection
    @CollectionTable(name = "VoteValues_PlayerVotes", joinColumns = @JoinColumn(name = "date_vote_id"))
    @MapKeyJoinColumn(name = "player_id")
    @Column(name = "vote_value")
    private Map playerVotes;
}
