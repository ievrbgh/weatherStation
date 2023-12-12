package ru.ievrb.weatherStation.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.ievrb.weatherStation.dto.MeteringDTO;
import ru.ievrb.weatherStation.models.Metering;
import ru.ievrb.weatherStation.services.MeteringService;
import ru.ievrb.weatherStation.utill.exceptions.MeteringErrorResponce;
import ru.ievrb.weatherStation.utill.exceptions.MeteringNotCreatedException;
import ru.ievrb.weatherStation.utill.exceptions.MeteringNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.ievrb.weatherStation.utill.ErrorUtill.getErrorMessageString;

@RestController
@RequestMapping("/metering")
public class MeteringController {

    private final MeteringService ms;
    private final ModelMapper mm;

    @Autowired
    public MeteringController(MeteringService ms, ModelMapper mm) {
        this.ms = ms;
        this.mm = mm;
    }

    @GetMapping()
    public List<MeteringDTO> getMeteringList(){
        return ms.getAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MeteringDTO getMetering(@PathVariable("id") int id){
        return mm.map(ms.getById(id), MeteringDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeteringDTO meteringDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            throw new MeteringNotCreatedException(getErrorMessageString(bindingResult));
        }

        ms.save(convertToMetering(meteringDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private MeteringDTO convertToDTO(Metering metering){
        return mm.map(metering, MeteringDTO.class);
    }

    private Metering convertToMetering(MeteringDTO meteringDTO){
        return mm.map(meteringDTO, Metering.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeteringErrorResponce> handleException(MeteringNotFoundException e){
        MeteringErrorResponce mer = new MeteringErrorResponce(
                "Metering is not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(mer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeteringErrorResponce> handleException(MeteringNotCreatedException e){
        MeteringErrorResponce mer = new MeteringErrorResponce(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(mer, HttpStatus.BAD_REQUEST);
    }


}
