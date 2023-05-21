package com.dmvregistry.dmvregistry.repository;

import com.dmvregistry.dmvregistry.entitiy.Fine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FineDB {
    private Map<Integer, Fine> fines = new HashMap<>();

    public List<Fine> getAllFines() {
        return new ArrayList<>(fines.values());
    }

    public Fine getFineById(int id) {
        return fines.get(id);
    }

    public Fine addFine(Fine fine) {
        return fines.put(fine.getId(), fine);
    }

    public Fine updateFine(Fine updatedFine) {
        return fines.put(updatedFine.getId(), updatedFine);
    }

    public Fine deleteFine(int id) {
        return fines.remove(id);
    }
}
