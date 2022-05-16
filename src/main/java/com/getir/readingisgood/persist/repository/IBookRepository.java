package com.getir.readingisgood.persist.repository;

import com.getir.readingisgood.persist.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBookRepository extends MongoRepository<Book, String> {
}
