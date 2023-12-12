package ru.ievrb.weatherStation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ievrb.weatherStation.models.Metering;
import ru.ievrb.weatherStation.services.MeteringService;

import java.util.List;

@RestController
@RequestMapping("/metering")
public class MeteringController {

    private final MeteringService ms;

    @Autowired
    public MeteringController(MeteringService ms) {
        this.ms = ms;
    }

    @GetMapping()
    public List<Metering> getMeteringList(){
        return ms.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody Metering metering) {

        ms.save(metering);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
