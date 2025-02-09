package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectAndWriteData {
	private List<Person> dataForWriting = new ArrayList<>();
	private List<Person> fullData = new ArrayList<>();
	private final Path inputPath;
	private int month;
	private Path outputPath;


	public SelectAndWriteData(int month) {
		String inputFile = "input.csv";
		String outputFile = "output.csv";
		this.inputPath = Paths.get(inputFile);
		this.month = month;
		this.outputPath = Paths.get(outputFile);
	}

	private List<Person> getResultsFromInputFile() {
		FileParser fileParser = new FileParser();
		try {
			fileParser.readFile(inputPath);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		fullData = fileParser.parseFileContent();
		return fullData;
	}

	private List<Person> selectNamesFromPersonsInMonth() {
		getResultsFromInputFile();
		boolean monthExists = false;
		for (Person person : fullData) {
			if (person.getBirthDate().getMonthValue() == month) {
				monthExists = true;
			}
		}
		if (!monthExists) {
			System.out.println("There is no person with birth month: " + month);
			System.exit(1);
		}
		dataForWriting = fullData.stream()
				.filter(person -> person.getBirthDate().getMonthValue() == month)
				.map(person -> new Person(person.getFirstName(), person.getLastName()))
				.collect(Collectors.toList());

		return dataForWriting;
	}

	public boolean writeData() {
		selectNamesFromPersonsInMonth();
		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writeData(outputPath, dataForWriting);
		return true;
	}


}
