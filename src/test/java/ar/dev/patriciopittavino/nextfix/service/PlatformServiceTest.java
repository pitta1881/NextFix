package ar.dev.patriciopittavino.nextfix.service;

import ar.dev.patriciopittavino.nextfix.model.Platform;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class PlatformServiceTest {

    private final PlatformService platformService;
    private Platform savedPlatform;

    @BeforeEach
    void setup() {
        Platform platform = new Platform();
        platform.setName("Netflix");
        platform.setCurrency("USD");
        platform.setPrice(new BigDecimal("10.99"));
        platform.setUrl("https://www.netflix.com");
        savedPlatform = platformService.savePlatform(platform);
    }

    @Test
    void savePlatform() {
        assertNotNull(savedPlatform.getId());
        assertEquals("Netflix", savedPlatform.getName());
        assertEquals("USD", savedPlatform.getCurrency());
        assertEquals(new BigDecimal("10.99"), savedPlatform.getPrice());
        assertEquals("https://www.netflix.com", savedPlatform.getUrl());
    }

    @Test
    void getPlatformById() {
        Platform platform = platformService.getPlatformById(savedPlatform.getId());
        assertEquals(savedPlatform.getId(), platform.getId());
        assertEquals(savedPlatform.getName(), platform.getName());
        assertEquals(savedPlatform.getCurrency(), platform.getCurrency());
        assertEquals(savedPlatform.getPrice(), platform.getPrice());
        assertEquals(savedPlatform.getUrl(), platform.getUrl());
    }

    @Test
    void getAllPlatforms() {
        assertFalse(platformService.getAllPlatforms().isEmpty());
    }

    @Test
    void deletePlatform() {
        platformService.deletePlatform(savedPlatform.getId());
        Exception exception = assertThrows(RuntimeException.class, () -> platformService.getPlatformById(savedPlatform.getId()));
        assertEquals("Platform not found", exception.getMessage());
    }
}
