package com.epam.Incrementor.cache;

import com.epam.Incrementor.domain.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class Cache {

    private final Map<Request, Integer> cache = new HashMap<>();

    public Optional<Integer> getResult(Request request) {
        Integer result = cache.get(request);
        if (result != null) {
            log.info("Cache returned a result: " + result + " with operation: " + request.getOperation());
        }
        return Optional.ofNullable(cache.get(request));
    }

    public void putResult(Request request) {
        log.info("Cache received a result: " + request.getResult() + " with operation: " + request.getOperation());
        cache.put(request, request.getResult());
    }

}
