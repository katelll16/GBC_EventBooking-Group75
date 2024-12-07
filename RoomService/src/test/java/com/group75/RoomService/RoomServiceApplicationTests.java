import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.DynamicPropertyRegistry;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RoomServiceApplicationTests {

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("test_db")
			.withUsername("user")
			.withPassword("password");

	@BeforeAll
	static void startContainer() {
		System.out.println("Starting PostgreSQL container...");
		postgres.start();
		System.out.println("PostgreSQL container started!");
	}

	@DynamicPropertySource
	static void databaseProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);

		System.out.println("JDBC URL: " + postgres.getJdbcUrl());
		System.out.println("Username: " + postgres.getUsername());
		System.out.println("Password: " + postgres.getPassword());
	}

	@Test
	void contextLoads() {
	}
}