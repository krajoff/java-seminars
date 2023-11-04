package fourth.message;


import org.junit.jupiter.api.Test;
import seminars.fourth.message.MessageService;
import seminars.fourth.message.NotificationService;

import static org.mockito.Mockito.*;

class NotificationServiceTest {
    @Test
    void testMessage() {
        MessageService messageService = mock(MessageService.class);
        NotificationService notificationService = new NotificationService(messageService);
        notificationService.sendNotification("Hi", "Good bye");
        verify(messageService, times(1)).
                sendMessage("Hi", "Good bye");
    }
}