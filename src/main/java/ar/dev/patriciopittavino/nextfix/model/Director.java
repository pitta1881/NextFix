package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data   //toString, equal, hashcode, getters, setters
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Nacionality is mandatory")
    @Size(min = 3, max = 50, message = "Nacionality must be between 3 and 50 characters")
    private String nacionality;

    @NotNull(message = "Birthdate is mandatory")
    private LocalDate birthdate;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private List<Movie> directedMovies = new ArrayList<>();

    @OneToOne(mappedBy = "director", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserCustom user;
}
