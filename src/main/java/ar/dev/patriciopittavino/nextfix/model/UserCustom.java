package ar.dev.patriciopittavino.nextfix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   //toString, equal, hashcode, getters, setters
@NoArgsConstructor
@AllArgsConstructor
public class UserCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least 8 characters, one digit, one lowercase and one uppercase letter")
    private String password;

    private String role;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Director director;
}
