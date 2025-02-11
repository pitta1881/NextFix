package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    private String moneda;  // ars, usd, eur, etc.

    private String enlace;

    @ManyToMany(mappedBy = "plataformasDisponibles", fetch = FetchType.EAGER)
    private List<Pelicula> peliculasDisponibles = new ArrayList<>();
}
