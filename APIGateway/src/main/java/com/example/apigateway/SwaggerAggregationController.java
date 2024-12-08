import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SwaggerAggregationController {

    private final RestTemplate restTemplate;

    public SwaggerAggregationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/v2/api-docs")
    public String aggregateApiDocs() {
        // Fetch Swagger JSON from each microservice
        String bookingServiceDocs = restTemplate.getForObject("http://booking-service/v2/api-docs", String.class);
        String eventServiceDocs = restTemplate.getForObject("http://event-service/v2/api-docs", String.class);
        // Combine the JSON strings (you may need to parse and merge them properly)
        return combineSwaggerDocs(bookingServiceDocs, eventServiceDocs);
    }

    private String combineSwaggerDocs(String... docs) {
        // Implement logic to combine the Swagger JSON documents
        return "{...}"; // Return the combined JSON
    }
}