package com.epam.Incrementor.domain;

import javax.validation.constraints.NotNull;

public enum Operation {
    INCREMENT(1), DECREMENT(-1), ADD_TEN(10), SUB_TEN(-10);

    @NotNull(message = "Выберите операцию")
    private final Integer value;

    Operation(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
