package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.exception.NoBookFoundException;
import com.getir.readingisgood.exception.NotEnoughCopyException;
import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.repository.IBookRepository;
import com.getir.readingisgood.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String id) throws BaseException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new NoBookFoundException(id);
        }
        return bookOptional.get();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book sellBook(String id, Integer quantity) throws BaseException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new NoBookFoundException(id);
        }
        Book book = bookOptional.get();
        if (book.getStock() < quantity) {
            throw new NotEnoughCopyException(id, book.getStock());
        }
        book.setStock(book.getStock() - quantity);
        return bookRepository.save(book);
    }
}
