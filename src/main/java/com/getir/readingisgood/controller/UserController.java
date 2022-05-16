package com.getir.readingisgood.controller;

import com.getir.readingisgood.controller.form.UserForm;
import com.getir.readingisgood.controller.form.UserLoginForm;
import com.getir.readingisgood.dto.TokenDto;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.model.User;
import com.getir.readingisgood.service.IOrderService;
import com.getir.readingisgood.service.IUserService;
import com.getir.readingisgood.utils.JsonResponse;
import com.getir.readingisgood.utils.mapper.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for user interactions
 */
@Api(value = "User Controller")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final ObjectMapper mapper;

    private final IOrderService orderService;

    @ApiOperation(value = "Create user",
            notes = "This method creates a new user")
    @PostMapping("/signup")
    public ResponseEntity<JsonResponse> createUser(@RequestBody @Validated UserForm userForm) throws BaseException {
        User user = userService.createUser(mapper.formToUser(userForm));
        return JsonResponse.success(mapper.userToDto(user)).toResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Login",
            notes = "This method helps user to login")
    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody @Validated UserLoginForm userLoginForm) throws BaseException {
        TokenDto token = userService.login(mapper.formToLoginDto(userLoginForm));
        return JsonResponse.success(token).toResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/order")
    @ApiOperation(value = "all order of user")
    public ResponseEntity<JsonResponse> getUserOrders(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) throws BaseException {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return JsonResponse.success(orderService.findAll(pageable)).toResponseEntity(HttpStatus.OK);
    }

}
