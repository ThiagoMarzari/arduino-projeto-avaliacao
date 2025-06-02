package com.thiago.arduino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArduinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArduinoApplication.class, args);
//
//		ControlePorta controle = new ControlePorta("COM5", 9600); // Substitua "COM3" pela sua porta COM
//
//		// Enviar dados
//		controle.enviaDados("1"); // Liga o LED
//
//		// Fechar a porta
//		controle.close();
	}

}

