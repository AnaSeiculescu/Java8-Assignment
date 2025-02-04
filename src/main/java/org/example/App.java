package org.example;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FileParsing fileParsing = new FileParsing();
//        fileParsing.parseFile();
        System.out.println( fileParsing.parseFileContent() );
    }
}
