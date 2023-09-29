package fourth.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.fourth.book.*;
import seminars.fourth.database.DataProcessor;
import seminars.third.coverage.SomeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    /**
     * У вас есть класс BookService, который использует интерфейс
     * BookRepository для получения информации о книгах из базы
     * данных. Ваша задача написать unit-тесты для BookService,
     * используя Mockito для создания мок-объекта BookRepository.
     */
    BookRepository bookRepository;
    BookService bookService;
    List<Book> books = new InMemoryBookRepository().findAll();


    @BeforeEach
    void testInit() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testFindAllBooks() {
        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(books, bookService.findAllBooks());
    }

    @Test
    void testFindBookById() {
        when(bookRepository.findById("1")).thenReturn(books.get(1));
        assertEquals(books.get(1), bookService.findBookById("1"));
    }

}