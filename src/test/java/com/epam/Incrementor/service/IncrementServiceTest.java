package com.epam.Incrementor.service;

import com.epam.Incrementor.cache.Cache;
import com.epam.Incrementor.domain.Operation;
import com.epam.Incrementor.domain.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncrementServiceTest {

    @Test
    public void testAddTen() {
        Request request = new Request();
        request.setValue(2);
        request.setOperation(Operation.ADD_TEN);
        IncrementService incrementService = new IncrementService(new Cache(), new CountService(), null);
        Request request1 = incrementService.calcResult(request);
        assertEquals(12, request1.getResult());
    }

    @Test
    public void testSubTen() {
        Request request = new Request();
        request.setValue(2);
        request.setOperation(Operation.SUB_TEN);
        IncrementService incrementService = new IncrementService(new Cache(), new CountService(), null);
        Request request1 = incrementService.calcResult(request);
        assertEquals(-8, request1.getResult());
    }

}