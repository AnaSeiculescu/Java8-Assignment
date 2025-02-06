package org.example;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectData {
	private List<Person> dataForWriting = new ArrayList<>();
	private List<Person> fullData = new ArrayList<>();
	private int month = 4;

	public List<Person> getResultsFromInputFile() {
		String inputFile = "input.csv";
		FileParsing fileParsing = new FileParsing();
		fileParsing.setPath(Paths.get(inputFile));
		fullData = fileParsing.parseFileContent();
		return fullData;
	}

	public List<Person> selectNamesFromPersons() {
		getResultsFromInputFile();
		dataForWriting = fullData.stream()
				.filter(person -> person.getBirthDate().getMonthValue() == 4)
				.map(person -> new Person(person.getFirstName(), person.getLastName()))
				.collect(Collectors.toList());

		return dataForWriting;
	}

}
