package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dummy.BookDummy;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.repository.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    IBookRepository bookRepository;


    BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void findAll() {
        List<Book> books = BookDummy.listOfBooks();
        when(bookRepository.findAll()).thenReturn(books);
        //
        List<Book> values = bookService.findAll();
        //
        assertEquals(books.size(), values.size());
        assertEquals(books.get(0).getId(), values.get(0).getId());
    }

    @Test
    void findById() {
        Optional<Book> book = Optional.of(BookDummy.singleBook());
        when(bookRepository.findById(Mockito.anyString())).thenReturn(book);
        //
        try {
            Book value = bookService.findById("id_1");
            //
            assertEquals(book.get().getId(), value.getId());
        } catch (BaseException e) {
            fail("Unexpected BaseException ", e);
        }
    }

    @Test
    void createBook() {
        bookService.createBook(BookDummy.singleBook());
        //
        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @Test
    void sellBook() {
        Book book = BookDummy.singleBook();
        Optional<Book> bookOp = Optional.of(BookDummy.singleBook());
        when(bookRepository.findById(Mockito.anyString())).thenReturn(bookOp);
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        //
        try {
            Book value = bookService.sellBook("id_1", 3);
            assertEquals(book.getId(), value.getId());
        } catch (BaseException e) {
            fail("Unexpected BaseException " + e);
        }
    }
}