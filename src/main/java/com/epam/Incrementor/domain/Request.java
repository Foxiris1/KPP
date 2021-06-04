package com.epam.Incrementor.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Перменаня X не должны быть пустой")
    private Integer value;

    @NotNull(message = "Укажите операцию")
    @Valid
    @Enumerated(EnumType.ORDINAL)
    private Operation operation;

    private Integer result;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(value, request.value) && operation == request.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, operation);
    }

    @Override
    public String toString() {
        return "Request{" +
                "value='" + value + '\'' +
                ", operation=" + operation +
                ", result=" + result +
                '}';
    }

}
