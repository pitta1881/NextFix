package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String titulo;

    private String genero;

    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @ToString.Exclude
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "pelicula_plataforma",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id")
    )
    @ToString.Exclude
    private List<Plataforma> plataformasDisponibles = new ArrayList<>();
}
