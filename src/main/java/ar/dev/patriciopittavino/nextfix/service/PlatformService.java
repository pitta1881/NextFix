package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.model.Movie;
import ar.dev.patriciopittavino.nextfix.model.Platform;
import ar.dev.patriciopittavino.nextfix.repository.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService {

    PlatformRepository platformRepository;

    public Platform getPlatformById(Long id) {
        return platformRepository.findById(id).orElseThrow(() -> new RuntimeException("Platform not found"));
    }

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public Platform savePlatform(Platform platform) {
        return platformRepository.save(platform);
    }

    @Transactional
    public void deletePlatform(Long id) {
        Platform platform = platformRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Platform not found"));

        for (Movie movie : platform.getAvalaibleMovies()) {
            movie.getAvalaiblePlatforms().remove(platform);
        }

        platform.getAvalaibleMovies().clear();

        platformRepository.delete(platform);
    }
}
