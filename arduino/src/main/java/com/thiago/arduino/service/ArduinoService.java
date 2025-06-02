package com.thiago.arduino.service;

import com.thiago.arduino.model.Arduino;
import gnu.io.SerialPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ArduinoService {

    private SerialPort serialPort;
    private OutputStream outputStream;

    private final String portaCOM = "COM2";
    private final int taxa = 9600;

    public ArduinoService() {
        this.initialize();
    }

    private void initialize() {
        try {
            CommPortIdentifier portId = null;
            try {
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
            } catch (NoSuchPortException npe) {
                System.err.println("Porta COM não encontrada: " + this.portaCOM);
                return;
            }
            serialPort = (SerialPort) portId.open("Comunicação serial", this.taxa);
            outputStream = serialPort.getOutputStream();
            serialPort.setSerialPortParams(this.taxa, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            System.out.println("Porta serial inicializada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (outputStream != null) outputStream.close();
        } catch (IOException e) {
            System.err.println("Não foi possível fechar porta COM.");
        }
    }

    public void enviarDado(String opcao) {
        try {
            if (outputStream != null) {
                outputStream.write(opcao.getBytes());
                outputStream.flush();
            } else {
                System.err.println("Stream de saída nao esta configurada");
            }
        } catch (IOException ex) {
            System.err.println("Não foi possível enviar o dado.");
        }
    }
}

