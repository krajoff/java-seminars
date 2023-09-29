package fourth.database;

import org.junit.jupiter.api.Test;
import seminars.fourth.database.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DataProcessorTest {
    @Test
    void testDataBase() {
        Database database = mock(Database.class);
        List<String> fakeData = Arrays.asList("Data1", "Data2", "Data3");
        when(database.query(anyString())).thenReturn(fakeData);
        DataProcessor dataProcessor = new DataProcessor(database);
        List<String> rs = dataProcessor.processData("SELECT * FROM table");
        assertEquals(fakeData, rs);
    }

}