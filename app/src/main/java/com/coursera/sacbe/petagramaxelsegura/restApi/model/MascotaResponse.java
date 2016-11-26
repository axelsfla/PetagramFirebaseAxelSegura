package com.coursera.sacbe.petagramaxelsegura.restApi.model;

import com.coursera.sacbe.petagramaxelsegura.pojo.FotoMascota;
import com.coursera.sacbe.petagramaxelsegura.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by axel on 30/09/2016.
 */
public class MascotaResponse {

    private ArrayList<FotoMascota> mascotas;
    //private ArrayList<>
    private FotoMascota userInformation;

    public ArrayList<FotoMascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<FotoMascota> mascotas) {
        this.mascotas = mascotas;
    }

    public FotoMascota getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(FotoMascota userInformation) {
        this.userInformation = userInformation;
    }
}
