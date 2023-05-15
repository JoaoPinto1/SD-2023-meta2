package com.example.demo.Forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class termos {

    public List<String> Termos;
    public boolean checked;


    public termos(){
        Termos = new ArrayList<>();
        checked = false;
    }

    public termos(List<String> termos , Boolean check) {
        Termos = termos;
        checked = check;
    }

    public void setTermos(String termos) {
        Termos.add(termos);
    }


    public void setChecked(boolean check){
        checked = check;
    }

    @Override
    public String toString() {
        return "termos{" +
                "Termos=" + Termos +
                ", checked=" + checked +
                '}';
    }

    public List<String> getTermos() {
        return Termos;
    }
}
