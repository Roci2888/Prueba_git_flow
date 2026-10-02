package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Reporte {
ArrayList<RegistroCovid> registros;

public static void imprimirDatos(){

}
public static Map<String, Long> activosPorMesYPais(List<RegistroCovid> registros) {
    Map<String, Long> activosPorMesYPais = registros.stream()
            .collect(Collectors.groupingBy(
                    r -> r.getFecha().getYear() + "-" + String.format("%02d", r.getFecha().getMonthValue()) + ";" + r.getPais() + ";" + r.getRegion(),
                    Collectors.summingLong(RegistroCovid::getActivos)
            ));
    return activosPorMesYPais;
}

    public static Map<String, RegistroCovid> diaMasMuertesPorPais(List<RegistroCovid> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(
                        RegistroCovid::getPais,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(RegistroCovid::getMuertes)),
                                Optional::orElseThrow
                        )
                ));
    }

    public static void generarResultado1(Path rutaSalida, Map<String, Long> datos) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add("### CASOS ACTIVOS MENSUALES POR PAIS ###");
        lineas.add("### INICIO ARCHIVO ###");
        lineas.add("mes pais;region;cantidad activos");

        datos.forEach((clave, totalActivos) -> {
            // clave viene estructurada como "YYYY-MM;Pais;Region"
            lineas.add(clave + ";" + totalActivos);
        });

        lineas.add("### FIN ARCHIVO ###");
        Files.write(rutaSalida.resolve("resultado1.txt"), lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void generarResultado2(Path rutaSalida, Map<String, RegistroCovid> maximos) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add("### MAYOR CANTIDAD DE MUERTES POR CADA PAIS###");
        lineas.add("### INICIO ARCHIVO ###");
        lineas.add("dia;mes;pais; region;cantidad muertes");

        maximos.values().forEach(reg -> {
            String dia = String.format("%02d", reg.getFecha().getDayOfMonth());
            String mes = String.format("%02d", reg.getFecha().getMonthValue());
            lineas.add(dia + ";" + mes + ";" + reg.getPais() + ";" + reg.getRegion() + ";" + reg.getMuertes());
        });

        lineas.add("### FIN ARCHIVO ###");
        Files.write(rutaSalida.resolve("resultado2.txt"), lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}


