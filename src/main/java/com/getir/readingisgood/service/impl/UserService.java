package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.config.helper.JwtHelper;
import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.TokenDto;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.exception.LoginFailedException;
import com.getir.readingisgood.persist.exception.UserAlreadyExistsException;
import com.getir.readingisgood.persist.model.User;
import com.getir.readingisgood.persist.repository.IUserRepository;
import com.getir.readingisgood.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;

    @Override
    public User createUser(User userModel) throws BaseException {
        Optional<User> user = userRepository.findByEmail(userModel.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(userModel.getEmail());
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    @Override
    public TokenDto login(LoginDto loginDto) throws BaseException {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isEmpty()) {
            throw new LoginFailedException(loginDto.getEmail());
        }
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(), loginDto.getPassword()
                        )
                );
        return jwtHelper.generateJwtToken(loginDto.getEmail());
    }

    @Override
    public User findByEmail(String email) throws BaseException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new LoginFailedException(email);
        }
        return userOptional.get();
    }

    /**
     * Returns the currently logged in user
     * @return
     * @throws BaseException
     */
    @Override
    public User getCurrentUser() throws BaseException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return findByEmail(username);
    }

}
