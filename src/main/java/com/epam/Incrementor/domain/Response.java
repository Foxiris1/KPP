package com.epam.Incrementor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Response {

    private List<Request> requests;
    private Integer max;
    private Integer min;
    private Double avg;

}
