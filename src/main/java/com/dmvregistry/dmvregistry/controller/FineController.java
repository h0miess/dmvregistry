package com.dmvregistry.dmvregistry.controller;
import com.dmvregistry.dmvregistry.entitiy.Fine;
import com.dmvregistry.dmvregistry.repository.FineDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fines")
public class FineController {
    private FineDB fineDB;


    public FineController(FineDB fineDB) {
        this.fineDB = fineDB;
    }

    @GetMapping
    public List<Fine> allFines() {
        return fineDB.getAllFines();
    }

    @PostMapping
    public ResponseEntity<?> addFine(@RequestBody Fine fine) {
        fineDB.addFine(fine);
        return new ResponseEntity<>(fine, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateFine(@RequestBody Fine updatedFine) {
        Fine tmp = fineDB.getFineById(updatedFine.getId());
        if(tmp == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        fineDB.updateFine(updatedFine);
        return new ResponseEntity<>(updatedFine, HttpStatus.OK);
    }

    @DeleteMapping("/{fineId}")
    public ResponseEntity<?> deleteFine(@PathVariable int fineId) {
        Fine tmp = fineDB.getFineById(fineId);
        if(tmp == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        fineDB.deleteFine(fineId);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @GetMapping("/{fineId}")
    public ResponseEntity<?> getFineById(@PathVariable int fineId) {
        Fine tmp = fineDB.getFineById(fineId);
        if(tmp == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @PatchMapping("/{fineId}/pay")
    public ResponseEntity<?> payFine(@PathVariable int fineId) {
        Fine tmp = fineDB.getFineById(fineId);
        if(tmp == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!tmp.isPaid()) {
            tmp.pay();
            return new ResponseEntity<>("Fine is payed!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fine is already paid.", HttpStatus.OK);
    }

    @PatchMapping("/{fineId}/court")
    public ResponseEntity<?> sendCourt(@PathVariable int fineId) {
        Fine tmp = fineDB.getFineById(fineId);
        if (tmp == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!tmp.isCourt()) {
            tmp.sendCourt();
            return new ResponseEntity<>("Court is sent!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Court is already sent", HttpStatus.OK);
    }

}
