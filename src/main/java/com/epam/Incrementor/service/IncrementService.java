package com.epam.Incrementor.service;

import com.epam.Incrementor.cache.Cache;
import com.epam.Incrementor.domain.Operation;
import com.epam.Incrementor.domain.Request;
import com.epam.Incrementor.domain.Response;
import com.epam.Incrementor.repository.IncrementRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncrementService {

    private final Cache cache;
    private final CountService countService;
    private final IncrementRepository incrementRepository;

    public IncrementService(final Cache cache, final CountService countService, final IncrementRepository incrementRepository) {
        this.cache = cache;
        this.countService = countService;
        this.incrementRepository = incrementRepository;
    }

    public Request calcResult(Request request) {
        Optional<Integer> result = cache.getResult(request);
        if (result.isPresent()) {
            request.setResult(result.get());
        } else {
            Integer value = request.getValue();
            Operation operation = request.getOperation();
            request.setResult(value + operation.getValue());
            incrementRepository.save(request);
            cache.putResult(request);
        }
        return request;
    }

    public Request solve(Request request) {
        countService.incrementCounter();
        return calcResult(request);
    }

    public Response solveList(List<Request> requests) {
        countService.incrementCounter();
        List<Request> results = requests.stream()
                .map(this::calcResult)
                .collect(Collectors.toList());

        IntSummaryStatistics statistics = results.stream()
                .mapToInt(Request::getResult)
                .summaryStatistics();

        return new Response(results, statistics.getMax(), statistics.getMin(), statistics.getAverage());
    }

    public Integer getCount() {
        return countService.getCounter().intValue();
    }

    public List<Request> findAll(){
        return incrementRepository.findAll();
    }

    @PostConstruct
    public void init() {
        List<Request> requests = incrementRepository.findAll();
        requests.forEach(cache::putResult);
    }

}
