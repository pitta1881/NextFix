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
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private BigDecimal price;

    private String currency;  // ars, usd, eur, etc.

    private String url;

    @ManyToMany(mappedBy = "avalaiblePlatforms", fetch = FetchType.EAGER)
    private List<Movie> avalaibleMovies = new ArrayList<>();
}
