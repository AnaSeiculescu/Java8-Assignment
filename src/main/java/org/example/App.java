package org.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        DataManager selectAndWriteData = new DataManager(9);
        System.out.println(selectAndWriteData.writeData());
    }

}
