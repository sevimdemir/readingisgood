package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.IStatisticService;
import com.getir.readingisgood.utils.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Book Controller")
@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatisticController {

    private final IStatisticService statisticService;

    @GetMapping("/mothly")
    @ApiOperation(value = "gets monthly statistics")
    public ResponseEntity<JsonResponse> getAll() {
        return JsonResponse.success(statisticService.findAll()).toResponseEntity(HttpStatus.OK);
    }
}
