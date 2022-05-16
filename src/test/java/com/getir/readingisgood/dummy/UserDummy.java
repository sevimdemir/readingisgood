package com.getir.readingisgood.dummy;

import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.TokenDto;
import com.getir.readingisgood.persist.model.User;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDummy {

    public static User singleUser() {
        return singleUserWithId(1);
    }

    public static LoginDto singleLoginDto() {
        return new LoginDto(Faker.instance().internet().emailAddress(), Faker.instance().lorem().word());
    }

    public static TokenDto singleTokenDto() {
        return new TokenDto(Faker.instance().lorem().word());
    }

    public static Optional<User> singleUserOptional() {
        return Optional.of(singleUserWithId(1));
    }

    private static User singleUserWithId(int id) {
        return new User("id_" + id,
                Faker.instance().name().firstName(),
                Faker.instance().name().lastName(),
                Faker.instance().internet().emailAddress(),
                Faker.instance().lorem().word()
        );
    }

    public static List<User> listOfUsers() {
        return new ArrayList<>() {{
            add(singleUserWithId(1));
            add(singleUserWithId(2));
            add(singleUserWithId(3));
        }};

    }
}
