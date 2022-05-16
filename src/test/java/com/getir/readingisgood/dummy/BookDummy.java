package com.getir.readingisgood.dummy;

import com.getir.readingisgood.persist.model.Book;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookDummy {

    public static Book singleBook() {
        return singleBookWithId(1);
    }

    private static Book singleBookWithId(int id) {
        return new Book("id_" + id,
                Faker.instance().book().title(),
                Faker.instance().lorem().word(),
                BigDecimal.TEN,
                10);
    }

    public static List<Book> listOfBooks() {
        return new ArrayList<>() {{
            add(singleBookWithId(1));
            add(singleBookWithId(2));
            add(singleBookWithId(3));
        }};

    }
}
