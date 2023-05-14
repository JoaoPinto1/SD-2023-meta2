package com.example.demo.Forms;

import java.util.Arrays;

public class termos {

    public String[] Termos;


    public termos(){

    }

    @Override
    public String toString() {
        return "termos{" +
                "termos=" + Arrays.toString(Termos) +
                '}';
    }

    public termos(String[] termos) {
        this.Termos = termos;
    }

    public String[] getTermos() {
        return Termos;
    }
}
