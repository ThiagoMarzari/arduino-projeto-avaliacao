package com.thiago.arduino.model;

import java.io.OutputStream;

public class Arduino {
    private OutputStream serialOut;

    public OutputStream getSerialOut() {
        return serialOut;
    }

    public int getTaxa() {
        return taxa;
    }

    public String getPortaCOM() {
        return portaCOM;
    }

    private int taxa;
    private String portaCOM;

    public Arduino(String portaCOM, int taxa) {
        this.taxa = taxa;
        this.portaCOM = portaCOM;
    }


}
