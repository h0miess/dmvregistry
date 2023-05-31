package com.dmvregistry.dmvregistry.repository;

import com.dmvregistry.dmvregistry.entitiy.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Integer> {
}
