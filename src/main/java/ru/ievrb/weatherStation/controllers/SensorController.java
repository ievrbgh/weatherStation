package ru.ievrb.weatherStation.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ievrb.weatherStation.dto.SensorDTO;
import ru.ievrb.weatherStation.models.Sensor;
import ru.ievrb.weatherStation.services.SensorService;
import ru.ievrb.weatherStation.utill.exceptions.SensorErrorResponce;
import ru.ievrb.weatherStation.utill.exceptions.SensorNotCreatedException;
import ru.ievrb.weatherStation.utill.ErrorUtill;


import static ru.ievrb.weatherStation.utill.ErrorUtill.getErrorMessageString;
import static ru.ievrb.weatherStation.utill.TokenGenerator.generateToken;

@RestController
@RequestMapping("/scanner")
public class SensorController {

    private final SensorService ss;
    private final ModelMapper mm;

    @Autowired
    public SensorController(SensorService ss, ModelMapper mm) {
        this.ss = ss;
        this.mm = mm;
    }

    @PostMapping("/register")
    private ResponseEntity<String> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new SensorNotCreatedException(getErrorMessageString(bindingResult));
        }

        Sensor sensor = convertToSensor(sensorDTO);
        ss.save(sensor);

        return ResponseEntity.ok(sensor.getSensorToken());
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = mm.map(sensorDTO, Sensor.class);
        if(sensor.getSensorToken() == null) {
            sensor.setSensorToken(generateToken());
        }
        return sensor;
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponce> handlerException(SensorNotCreatedException e){

        SensorErrorResponce response = new SensorErrorResponce(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
