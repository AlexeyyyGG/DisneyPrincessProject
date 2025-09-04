package com.example.controllers;

import com.example.service.PrincessService;
import java.util.List;
import model.Princess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/princesses")
public class PrincessController {
    private final PrincessService service;

    @Autowired
    public PrincessController(PrincessService service) {
        this.service = service;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrincesses(
            @RequestParam(value = "id", required = false) Integer id) {
        try {
            if (id != null) {
                Princess princess = service.getPrincess(id);
                if (princess == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Princess not found");
                }
                return ResponseEntity.status(HttpStatus.OK).body(princess);
            } else {
                List<Princess> princesses = service.getAllPrincess();
                return ResponseEntity.status(HttpStatus.OK).body(princesses);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPrincess(@RequestBody Princess princess) {
        try {
            service.addPrincess(princess);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePrincess(@RequestBody Princess princess) {
        try {
            service.updatePrincess(princess);
            return ResponseEntity.status(HttpStatus.OK).body(princess);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePrincess(@RequestBody Princess princess) {
        try {
            service.deletePrincess(princess.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

