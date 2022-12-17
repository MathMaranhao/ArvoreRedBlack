package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManipuladorArquivos {

    public static List<String> lerArquivo(String caminho) throws IOException {

        Scanner scanner = new Scanner(new FileReader(caminho)).useDelimiter("\\n");
        List<String> linhasArquivo = new ArrayList<>();

        while (scanner.hasNext()) {
            linhasArquivo.add(scanner.next());
        }

        return linhasArquivo;
    }

}
