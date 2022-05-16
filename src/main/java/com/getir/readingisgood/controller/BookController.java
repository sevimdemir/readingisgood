package com.getir.readingisgood.controller;

import com.getir.readingisgood.controller.form.BookForm;
import com.getir.readingisgood.controller.form.BookUpdateForm;
import com.getir.readingisgood.persist.exception.BaseException;
import com.getir.readingisgood.service.IBookService;
import com.getir.readingisgood.utils.JsonResponse;
import com.getir.readingisgood.utils.mapper.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "Book Controller")
@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;
    private final ObjectMapper mapper;

    @GetMapping("/all")
    @ApiOperation(value = "list of books")
    public ResponseEntity<JsonResponse> getAll() {
        return JsonResponse.success(bookService.findAll()).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get single book with id")
    public ResponseEntity<JsonResponse> getById(@PathVariable String id) throws BaseException {
        return JsonResponse.success(bookService.findById(id)).toResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/new")
    @ApiOperation(value = "create single book")
    public ResponseEntity<JsonResponse> addBook(@RequestBody @Validated BookForm bookForm) {
        return JsonResponse.success(bookService.createBook(mapper.formToBook(bookForm))).toResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation(value = "update single book with id")
    public ResponseEntity<JsonResponse> updateBook(@RequestBody @Validated BookUpdateForm bookUpdateForm) throws BaseException {
        return JsonResponse.success(bookService.updateBook(mapper.updateFormToBook(bookUpdateForm))).toResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
