package com.example.demo.Forms;

import java.util.ArrayList;
import java.util.List;

public class Termos {

    public List<String> termos;
    public boolean checked;


    public Termos(){
        termos = new ArrayList<>();
        checked = false;
    }

    public Termos(List<String> termos , Boolean check) {
        termos = termos;
        checked = check;
    }

    public void setTermos(String new_termos) {
        termos.add(new_termos);
    }


    public void setChecked(boolean check){
        checked = check;
    }

    @Override
    public String toString() {
        return "termos{" +
                "Termos=" + termos +
                ", checked=" + checked +
                '}';
    }

    public List<String> getTermos() {
        return termos;
    }
}
