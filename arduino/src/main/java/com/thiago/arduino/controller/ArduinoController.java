package com.thiago.arduino.controller;

import com.thiago.arduino.ControlePorta;
import com.thiago.arduino.service.ArduinoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/led")
public class ArduinoController {

//    private final ArduinoService arduinoService;
    private ControlePorta controlePorta;

//    public ArduinoController(ArduinoService arduinoService) {
//        this.arduinoService = arduinoService;
//    }

    @GetMapping()
    public String controlarLed() {
       return "index";
    }

    @GetMapping("/desligar")
    @ResponseBody
    public String enviaDados() {
//        arduinoService.enviarDado("2");
        controlePorta = new ControlePorta("COM5", 9600); // Substitua "COM5" pela sua porta COM
        controlePorta.enviaDados("2");
        controlePorta.close();
        return "OK";
    }

    @GetMapping("/ligar")
    @ResponseBody
    public String ligarLed() {
        controlePorta = new ControlePorta("COM5", 9600); // Substitua "COM5" pela sua porta COM
        controlePorta.enviaDados("1");
        controlePorta.close();
        return "OK";
    }
}
