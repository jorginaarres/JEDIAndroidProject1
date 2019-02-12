package com.jediupc.helloandroid.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Assignatura implements Serializable {
    public String nomAssignatura;
    public ArrayList<Actes> actes;

    public Assignatura(String nomAssignatura) {
        this.nomAssignatura = nomAssignatura;
        this.actes= new ArrayList<>();

    }

}
