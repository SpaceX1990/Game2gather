package de.szut.game2gather_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Datevote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DateVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime voteoption;

    @OneToMany
    @JoinTable(
            name = "datevote_votes",
            joinColumns = @JoinColumn(name = "datevote_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    List<UserVote> userVotes;

    @JoinColumn(name = "session_id")
    private int session_id;
}
