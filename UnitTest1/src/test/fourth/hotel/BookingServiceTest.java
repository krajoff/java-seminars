package fourth.hotel;


import org.junit.jupiter.api.Test;
import seminars.fourth.hotel.BookingService;
import seminars.fourth.hotel.HotelService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceTest {
    @Test
    void testAvailableRoom() {
        HotelService hotelService = mock(HotelService.class);
        when(hotelService.isRoomAvailable(anyInt())).thenReturn(true);
        BookingService bookingService = new BookingService(hotelService);
        assertTrue(bookingService.bookRoom(404));

    }
}