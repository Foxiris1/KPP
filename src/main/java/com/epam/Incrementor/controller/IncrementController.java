package com.epam.Incrementor.controller;

import com.epam.Incrementor.domain.Request;
import com.epam.Incrementor.domain.Response;
import com.epam.Incrementor.service.IncrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class IncrementController {

    @Autowired
    private IncrementService incrementService;

    @GetMapping("/increment")
    public Map<String, Object> increment(@Valid Request request) {
        request = incrementService.solve(request);
        return Map.of("result", request.getResult());
    }

    @PostMapping("/increment")
    public Response incrementBulk(@RequestBody List<@Valid Request> requests) {
        return incrementService.solveList(requests);
    }

    @GetMapping("/counter")
    public Integer getCount() {
        return incrementService.getCount();
    }

    @GetMapping("/findAll")
    public List<Request> findAll() {
        return incrementService.findAll();
    }

}
