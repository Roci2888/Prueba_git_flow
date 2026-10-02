package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    public List<RegistroCovid> cargarDatos(Path rutaArchivo) throws IOException {
        List<RegistroCovid> lista = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            reader.readLine(); // Saltear cabecera
            while ((linea = reader.readLine()) != null) {
                String[] tokens = linea.split(",");
                if (tokens.length >= 7) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fecha = LocalDate.parse(tokens[0].trim(), formatter); // Formato YYYY-MM-DD
                    String pais = tokens[1].trim();
                    int confirmados = Integer.parseInt(tokens[2].trim());
                    int muertes = Integer.parseInt(tokens[3].trim());
                    int recuperados = Integer.parseInt(tokens[4].trim());
                    int activos = Integer.parseInt(tokens[5].trim());
                    String region = tokens[6].trim();

                    lista.add(new RegistroCovid(fecha, pais, confirmados, muertes, recuperados, activos, region));
                }
            }
        }
        return lista;
    }
}
