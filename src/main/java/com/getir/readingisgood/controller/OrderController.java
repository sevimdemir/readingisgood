package com.getir.readingisgood.controller;

import com.getir.readingisgood.controller.form.OrderItemForm;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.persist.model.OrderDetail;
import com.getir.readingisgood.service.IOrderService;
import com.getir.readingisgood.utils.JsonResponse;
import com.getir.readingisgood.utils.mapper.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @ApiOperation(value = "creates new order")
    public ResponseEntity<JsonResponse> order(@RequestBody @Validated List<OrderItemForm> orderItems) throws BaseException {
        List<OrderDetail> orderDetails = orderItems.stream().map(item -> mapper.formToOrderDetail(item)).collect(Collectors.toList());
        return JsonResponse.success(orderService.createOrder(orderDetails)).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "all order of user")
    public ResponseEntity<JsonResponse> getAll() throws BaseException {
        return JsonResponse.success(orderService.findAll()).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get single order with id")
    public ResponseEntity<JsonResponse> getById(@PathVariable String id) throws BaseException {
        return JsonResponse.success(orderService.findById(id)).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation(value = "get orders between given date interval")
    public ResponseEntity<JsonResponse> getById(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) throws BaseException {
        return JsonResponse.success(orderService.findByDateInterval(from, to)).toResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
