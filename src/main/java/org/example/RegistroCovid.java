package org.example;

import java.time.LocalDate;

public class RegistroCovid {

    private LocalDate fecha;
    private String pais;
    private int confirmados;
    private int muertes;
    private int recuperados;
    private int activos;
    private String region;

    public RegistroCovid(LocalDate fecha, String pais, int confirmados, int muertes, int recuperados, int activos, String region) {
        this.fecha = fecha;
        this.pais = pais;
        this.confirmados = confirmados;
        this.muertes = muertes;
        this.recuperados = recuperados;
        this.activos = activos;
        this.region = region;
    }

    // Constructor vacío (útil en caso de requerirse)
    public RegistroCovid() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getPais() {
        return pais;
    }

    public int getConfirmados() {
        return confirmados;
    }

    public int getMuertes() {
        return muertes;
    }

    public int getRecuperados() {
        return recuperados;
    }

    public int getActivos() {
        return activos;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "RegistroCovid{" +
                "fecha=" + fecha +
                ", pais='" + pais + '\'' +
                ", confirmados=" + confirmados +
                ", muertes=" + muertes +
                ", recuperados=" + recuperados +
                ", activos=" + activos +
                ", region='" + region + '\'' +
                '}';
    }
}
