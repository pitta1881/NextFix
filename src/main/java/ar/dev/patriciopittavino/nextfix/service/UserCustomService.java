package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.model.UserCustom;
import ar.dev.patriciopittavino.nextfix.repository.MovieRepository;
import ar.dev.patriciopittavino.nextfix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final String ROLE_READ = "ROLE_READ";
    private final String ROLE_DIRECTOR = "ROLE_DIRECTOR";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCustom user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(user.getUsername()).password(user.getPassword()).authorities(List.of(new SimpleGrantedAuthority(user.getRole()))).build();
    }

    public UserCustom save(UserCustom user) {
        // if exists user
        UserCustom userDB = userRepository.findByUsername(user.getUsername());
        if (userDB != null) {
            throw new DataIntegrityViolationException("User already exists");
        }
        user.setRole(ROLE_READ);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        Optional<UserCustom> user = userRepository.findById(id);
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // if exists user and is not the same user
        if (user.isPresent()) {
            if (currentUsername.equals(user.get().getUsername())) {
                throw new IllegalArgumentException("You can't delete your own user");
            }
        }

        // Is Director: delete all movies associated
        if (user.get().getDirector() != null) {
            movieRepository.deleteByDirector(user.get().getDirector());
        }

        userRepository.deleteById(id);
    }

    public List<UserCustom> getAll() {
        return userRepository.findAll();
    }

    public List<UserCustom> getDirectors() {
        return userRepository.findByDirectorIsNotNull();
    }

    public UserCustom getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserCustom getUserByDirector(Director director) {
        return userRepository.findByDirector(director);
    }

    private PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public List<UserCustom> listUsersRegisteredWithDirectors() {
        return userRepository.findByDirectorIsNotNull();
    }

}
