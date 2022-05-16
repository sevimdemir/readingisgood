package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.config.helper.JwtHelper;
import com.getir.readingisgood.dummy.UserDummy;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.exception.LoginFailedException;
import com.getir.readingisgood.persist.exception.UserAlreadyExistsException;
import com.getir.readingisgood.persist.model.User;
import com.getir.readingisgood.persist.repository.IUserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtHelper jwtHelper;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    UserService userService;

    @Test
    void createUser() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn(Faker.instance().lorem().word());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(UserDummy.singleUser());
        //
        try {
            userService.createUser(UserDummy.singleUser());
            //
            verify(passwordEncoder, times(1)).encode(Mockito.anyString());
            verify(userRepository, times(1)).save(Mockito.any(User.class));
        } catch (BaseException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void createUser_alreadyExists() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(UserDummy.singleUserOptional());
        //
        try {
            userService.createUser(UserDummy.singleUser());
            //
            fail("Unexpected return");
        } catch (BaseException e) {
            assertEquals(UserAlreadyExistsException.class, e.getClass());
        }
    }

    @Test
    void login() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(UserDummy.singleUserOptional());
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(jwtHelper.generateJwtToken(Mockito.anyString())).thenReturn(UserDummy.singleTokenDto());
        //
        try {
            userService.login(UserDummy.singleLoginDto());
            //
            verify(authenticationManager, times(1)).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
            verify(jwtHelper, times(1)).generateJwtToken(Mockito.anyString());
        } catch (BaseException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void login_userNotFound() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        //
        try {
            userService.login(UserDummy.singleLoginDto());
            fail("Unexpected return");
        } catch (BaseException e) {
            assertEquals(LoginFailedException.class, e.getClass());
        }
    }

    @Test
    void findByEmail_failed() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

        try {
            userService.login(UserDummy.singleLoginDto());
            fail("Unexpected return");
        } catch (BaseException e) {
            assertEquals(LoginFailedException.class, e.getClass());
        }
    }
}