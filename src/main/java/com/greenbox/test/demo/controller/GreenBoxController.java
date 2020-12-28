package com.greenbox.test.demo.controller;

import com.greenbox.test.demo.entity.GreenBox;
import com.greenbox.test.demo.service.GreenBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreenBoxController {

    private final GreenBoxService greenBoxService;

    @Autowired
    public GreenBoxController(GreenBoxService greenBoxService) {
        this.greenBoxService = greenBoxService;
    }

    @PostMapping(value = "/green_boxes")
    public ResponseEntity<?> create(@RequestBody GreenBox greenBox) {
        greenBoxService.create(greenBox);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/green_boxes")
    public ResponseEntity<List<GreenBox>> read() {
        final List<GreenBox> greenBoxes = greenBoxService.readAll();

        return greenBoxes != null &&  !greenBoxes.isEmpty()
                ? new ResponseEntity<>(greenBoxes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/green_boxes/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody GreenBox greenBox) {
        final boolean updated = greenBoxService.update(greenBox, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/greenBoxes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = greenBoxService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
