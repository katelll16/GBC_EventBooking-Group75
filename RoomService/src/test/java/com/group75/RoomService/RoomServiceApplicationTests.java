package com.group75.RoomService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")  // Use the 'test' profile for tests
@SpringBootTest  // Load the application context for the test
public class RoomServiceApplicationTests {

    @Test
    void contextLoads() {
        // The test passes if the application context loads successfully
    }
}

