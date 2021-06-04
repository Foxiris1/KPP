package com.epam.Incrementor.repository;

import com.epam.Incrementor.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncrementRepository extends JpaRepository<Request, Long> {



}
