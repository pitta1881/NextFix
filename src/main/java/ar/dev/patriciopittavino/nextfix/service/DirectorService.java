package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.model.Director;
import ar.dev.patriciopittavino.nextfix.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorService {

    private DirectorRepository directorRepository;

    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElseThrow(() -> new RuntimeException("Director not found"));
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}
