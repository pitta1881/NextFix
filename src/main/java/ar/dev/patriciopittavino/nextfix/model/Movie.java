package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotNull(message = "Release Date is mandatory")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    @NotNull(message = "Director is mandatory")
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
