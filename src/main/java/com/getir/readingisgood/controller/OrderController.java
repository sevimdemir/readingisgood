package com.getir.readingisgood.controller;

import com.getir.readingisgood.controller.form.OrderItemForm;
import com.getir.readingisgood.exception.BaseException;
import com.getir.readingisgood.persist.model.Order;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.service.IOrderService;
import com.getir.readingisgood.utils.JsonResponse;
import com.getir.readingisgood.utils.mapper.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Order Controller")
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    private final ObjectMapper mapper;

    @PostMapping("/new")
    @ApiOperation(value = "get single book with id")
    public ResponseEntity<JsonResponse> addBook(@RequestParam @Validated List<OrderItemForm> orderItems) throws BaseException {
        List<OrderDetail> orderDetails = orderItems.stream().map(item -> mapper.formToOrderDetail(item)).collect(Collectors.toList());
        Order order = orderService.createOrder(orderDetails);
        return null;
    }

    @GetMapping("/all")
    @ApiOperation(value = "all order of user")
    public ResponseEntity<JsonResponse> getAll() throws BaseException {
        return JsonResponse.success(orderService.findAll()).toResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
