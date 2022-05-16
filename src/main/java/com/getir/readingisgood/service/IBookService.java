package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.persist.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(String id) throws BaseException;

    Book createBook(Book book);

    Book sellBook(String id, Integer quantity) throws BaseException;
}
