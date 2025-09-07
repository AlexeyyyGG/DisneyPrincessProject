package controller;

import java.util.List;
import model.Princess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PrincessService;

@RestController
@RequestMapping(PrincessController.PRINCESSES)
public class PrincessController {
    public static final String PRINCESSES = "/princesses";
    public static final String ID_PATH = "/{id}";
    public static final String ID = "id";
    private final PrincessService service;

    @Autowired
    public PrincessController(PrincessService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPrincesses() {
        List<Princess> princesses = service.getAllPrincess();
        return new ResponseEntity<>(princesses, HttpStatus.OK);
    }

    @GetMapping(value = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrincess(@PathVariable(ID) int id) {
        Princess princess = service.getPrincess(id);
        return new ResponseEntity<>(princess, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPrincess(@RequestBody Princess princess) {
        service.addPrincess(princess);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePrincess(
            @PathVariable(ID) int id,
            @RequestBody Princess princess
    ) {
        service.updatePrincess(id, princess);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = {ID_PATH})
    public ResponseEntity<?> deletePrincess(@PathVariable(ID) int id) {
        service.deletePrincess(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}