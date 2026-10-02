package org.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Uso correcto: java -jar mi_app.jar <carpeta_datasets> <carpeta_resultados>");
            System.exit(1);
        }

        Path carpetaDatasets = Paths.get(args[0]);
        Path carpetaResultados = Paths.get(args[1]);
        Path archivoCSV = carpetaDatasets.resolve("COVID19.csv");

        try {
            // 1. Crear directorio de salida si no existe
            Files.createDirectories(carpetaResultados);

            // 2. Leer datos
            LectorCSV lector = new LectorCSV();
            List<RegistroCovid> registros = lector.cargarDatos(archivoCSV);
            registros.forEach(System.out::println);
            // 3. Procesar y escribir reporte 1
            Map<String, Long> reporte1Data = Reporte.activosPorMesYPais(registros);
            Reporte.generarResultado1(carpetaResultados, reporte1Data);

            // 4. Procesar y escribir reporte 2
            Map<String, RegistroCovid> reporte2Data = Reporte.diaMasMuertesPorPais(registros);
            Reporte.generarResultado2(carpetaResultados, reporte2Data);

            System.out.println("Procesamiento completado exitosamente.");

        } catch (Exception e) {
            System.err.println("Error procesando la información: " + e.getMessage());
            e.printStackTrace();
        }
    }
}