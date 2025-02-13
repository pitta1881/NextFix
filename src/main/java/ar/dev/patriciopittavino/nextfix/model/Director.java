package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nacionality;

    private LocalDate birthdate;

    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private List<Movie> directedMovies = new ArrayList<>();
}
