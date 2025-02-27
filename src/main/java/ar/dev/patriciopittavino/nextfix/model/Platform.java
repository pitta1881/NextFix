package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data   //toString, equal, hashcode, getters, setters
@NoArgsConstructor
@AllArgsConstructor
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @NotBlank(message = "Currency is mandatory")
    private String currency;  // ars, usd, eur, etc.

    @NotBlank(message = "URL is mandatory")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    private String url;

    @ManyToMany(mappedBy = "avalaiblePlatforms", fetch = FetchType.EAGER)
    private List<Movie> avalaibleMovies = new ArrayList<>();
}
