package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        String inputFile = args[0];
////        int targetMonth = Integer.parseInt(args[1]);
////        String outputFile = args[2];
//
        getResultsFromFile(Paths.get(inputFile));
    }

    public static void getResultsFromFile(Path path) {
        FileParsing fileParsing = new FileParsing();
        List<Person> persons = fileParsing.parseFileContent(path);
        System.out.println(persons);
    }
}
