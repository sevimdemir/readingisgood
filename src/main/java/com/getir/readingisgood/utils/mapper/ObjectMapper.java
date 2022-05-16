package com.getir.readingisgood.utils.mapper;

import com.getir.readingisgood.controller.form.*;
import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.UserDto;
import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.persist.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    /**
     * maps @User object to @UserDto object
     *
     * @param user
     * @return
     */
    UserDto userToDto(User user);

    /**
     * maps @UserForm object to @User object
     *
     * @param userForm
     * @return
     */
    User formToUser(UserForm userForm);

    /**
     * maps @UserLoginForm to @LoginDto
     *
     * @param form
     * @return
     */
    LoginDto formToLoginDto(UserLoginForm form);

    /**
     * maps @BookForm to @Book
     *
     * @param form
     * @return
     */
    Book formToBook(BookForm form);

    /**
     * maps @BookForm to @Book
     *
     * @param form
     * @return
     */
    Book updateFormToBook(BookUpdateForm form);

    /**
     * maps @Book to @BookForm
     *
     * @param book
     * @return
     */
    BookForm bookToForm(Book book);

    /**
     * maps @OrderItemForm to @OrderDetail
     *
     * @param form
     * @return
     */
    OrderDetail formToOrderDetail(OrderItemForm form);
}
