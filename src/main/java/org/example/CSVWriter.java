package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CSVWriter {

	/**
	 * Write data boolean.
	 *
	 * @param outputPath     the output path
	 * @param dataForWriting the data for writing
	 * @return boolean
	 * Takes the output path and the list of selected people (first name and last name) and writes a CSV file with this data.
	 */
	public boolean writeData(Path outputPath, List<Person> dataForWriting) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(String.valueOf(outputPath)));
			for (Person person : dataForWriting) {
				writer.write(person.toString());
				writer.newLine();
			}
			System.out.println("CSV file created and written.");
			return true;
		} catch (IOException e) {
			System.out.println("Something went wrong creating the CSV: " + e.getMessage());
			return false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("Failed to close the writer: " + e.getMessage());
				}
			}
		}
	}

}
