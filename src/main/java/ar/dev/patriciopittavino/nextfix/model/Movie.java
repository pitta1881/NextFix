package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String genre;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "movie_platform",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    @ToString.Exclude
    @Builder.Default
    private List<Platform> avalaiblePlatforms = new ArrayList<>();
}
