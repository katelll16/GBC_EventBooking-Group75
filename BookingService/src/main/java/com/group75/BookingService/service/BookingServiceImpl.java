public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;

    private final KafkaTemplate<String, bookingPlacedEvent>g
}