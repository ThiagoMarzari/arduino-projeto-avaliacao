package com.thiago.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;

public class ControlePorta {
    private OutputStream serialOut;
    private int taxa;
    private String portaCOM;
    private SerialPort port;

    /**
     * Construtor da classe ControlePorta
     * @param portaCOM - Porta COM que será utilizada para enviar os dados para o arduino
     * @param taxa - Taxa de transferência da porta serial geralmente é 9600
     */
    public ControlePorta(String portaCOM, int taxa) {
        this.portaCOM = portaCOM;
        this.taxa = taxa;
        this.initialize();
    }

    /**
     * Método que verifica se a comunicação com a porta serial está ok
     */
    private void initialize() {
        try {
            CommPortIdentifier portId = null;
            try {
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
            } catch (NoSuchPortException npe) {
                System.err.println("Porta COM não encontrada.");
                return;
            }
            try {
                port = (SerialPort) portId.open("Comunicação serial", 2000); // timeout fixo de 2s
            } catch (PortInUseException piue) {
                System.err.println("Porta COM já está em uso.");
                return;
            }
            serialOut = port.getOutputStream();
            port.setSerialPortParams(this.taxa, // taxa de transferência da porta serial
                    SerialPort.DATABITS_8, // taxa de 10 bits 8 (envio)
                    SerialPort.STOPBITS_1, // taxa de 10 bits 1 (recebimento)
                    SerialPort.PARITY_NONE); // receber e enviar dados
        } catch (Exception e) {
            System.err.println("Erro ao inicializar a porta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método que fecha a comunicação com a porta serial
     */
    public void close() {
        try {
            if (serialOut != null) {
                serialOut.close();
            }
            if (port != null) {
                port.close();
            }
        } catch (IOException e) {
            System.err.println("Não foi possível fechar porta COM.");
        }
    }

    /**
     * @param opcao - Valor a ser enviado pela porta serial
     */
    public void enviaDados(String opcao) {
        try {
            if (serialOut != null) {
                serialOut.write(opcao.getBytes()); // escreve o valor na porta serial para ser enviado
            } else {
                System.err.println("Porta serial não inicializada.");
            }
        } catch (IOException ex) {
            System.err.println("Não foi possível enviar o dado.");
        }
    }
}

