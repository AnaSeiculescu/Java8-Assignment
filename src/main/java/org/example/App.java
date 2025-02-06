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

//        String inputFile = args[0];
        String inputFile = "input.csv";
////        int targetMonth = Integer.parseInt(args[1]);
////        String outputFile = args[2];
//

        SelectData selectData = new SelectData();
//        System.out.println(csvWriter.getResultsFromInputFile());
        System.out.println(selectData.selectNamesFromPersons());
    }

}
