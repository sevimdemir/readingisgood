package com.getir.readingisgood.utils.mapper;

import com.getir.readingisgood.controller.form.BookForm;
import com.getir.readingisgood.controller.form.OrderItemForm;
import com.getir.readingisgood.controller.form.UserForm;
import com.getir.readingisgood.controller.form.UserLoginForm;
import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.persist.model.Book;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.persist.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    /**
     * maps @User object to @UserForm Object
     *
     * @param user
     * @return
     */
    UserForm userToForm(User user);

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
