package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.TokenDto;
import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.exception.LoginFailedException;
import com.getir.readingisgood.exception.UserAlreadyExistsException;
import com.getir.readingisgood.persist.model.User;

public interface IUserService {
    /**
     * user creation
     *
     * @param userModel
     * @return
     */
    User createUser(User userModel) throws UserAlreadyExistsException, BaseException;

    /**
     * login for the system
     *
     * @param loginDto
     * @return
     */
    TokenDto login(LoginDto loginDto) throws LoginFailedException, BaseException;

    /**
     * find user by email address
     *
     * @param email
     * @return
     */
    User findByEmail(String email) throws BaseException;

    User getCurrentUser() throws BaseException;
}
