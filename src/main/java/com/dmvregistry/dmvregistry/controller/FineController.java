package com.dmvregistry.dmvregistry.controller;
import com.dmvregistry.dmvregistry.entitiy.Fine;
import com.dmvregistry.dmvregistry.repository.FineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fines")
public class FineController {
    private FineRepository fineRepository;


    public FineController(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @GetMapping
    public ResponseEntity<?> allFines() {
        List<Fine> fineList = fineRepository.findAll();
        return new ResponseEntity<>(fineList, HttpStatus.OK);
    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<?> finesWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        if(pageNumber < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page<Fine> page = fineRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));

        if(pageNumber > page.getTotalPages() && fineRepository.count() != 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if(fineRepository.count() == 0 && pageNumber != 1) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);

    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}/sort/{field}")
    public ResponseEntity<?> finesWithPaginationAndSort(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        if(pageNumber < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page<Fine> page = fineRepository.findAll(PageRequest.of(pageNumber - 1, pageSize).withSort(Sort.Direction.ASC, field));

        if(pageNumber > page.getTotalPages() && fineRepository.count() != 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if(fineRepository.count() == 0 && pageNumber != 1) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);

    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<?> getFinesWithSorting(@PathVariable String field) {
        List<Fine> fineListSorted = fineRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        return new ResponseEntity<>(fineListSorted, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addFine(@RequestBody Fine fine) {
        fineRepository.save(fine);
        return new ResponseEntity<>(fine, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateFine(@RequestBody Fine updatedFine) {
        fineRepository.save(updatedFine);
        return new ResponseEntity<>(updatedFine, HttpStatus.OK);
    }

    @DeleteMapping("/{fineId}")
    public ResponseEntity<?> deleteFine(@PathVariable int fineId) {
        try{
            Fine tmp = fineRepository.findById(fineId).orElseThrow(EntityNotFoundException::new);
            fineRepository.deleteById(fineId);
            return new ResponseEntity<>(tmp, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{fineId}")
    public ResponseEntity<?> getFineById(@PathVariable int fineId) {
        try {
            Fine tmp = fineRepository.findById(fineId).orElseThrow(EntityNotFoundException::new);
            return new ResponseEntity<>(tmp, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{fineId}/pay")
    public ResponseEntity<?> payFine(@PathVariable int fineId) {
        try {
            Fine tmp = fineRepository.findById(fineId).orElseThrow(EntityNotFoundException::new);
            if(tmp.isPaid()) {
                return new ResponseEntity<>("Fine is already payed!", HttpStatus.OK);
            }
            else {
                tmp.pay();
                fineRepository.save(tmp);
                return new ResponseEntity<>("Fine is payed!", HttpStatus.OK);
            }
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{fineId}/court")
    public ResponseEntity<?> sendCourt(@PathVariable int fineId) {
        try {
            Fine tmp = fineRepository.findById(fineId).orElseThrow(EntityNotFoundException::new);
            if(tmp.isCourt()) {
                return new ResponseEntity<>("Court is already sent", HttpStatus.OK);
            }
            else {
                tmp.sendCourt();
                fineRepository.save(tmp);
                return new ResponseEntity<>("Court is sent!", HttpStatus.OK);
            }
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
