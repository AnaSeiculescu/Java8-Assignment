package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
	private final Path inputPath;
	private int month;
	private Path outputPath;


	public DataManager(int month) {
		String inputFile = "input.csv";
		String outputFile = "output.csv";
		this.inputPath = Paths.get(inputFile);
		this.month = month;
		this.outputPath = Paths.get(outputFile);
	}

	private List<Person> getResultsFromInputFile() {
		FileParser fileParser = new FileParser();
		List<String> linesFromFile = new ArrayList<>();
		try {
			linesFromFile = fileParser.readFile(inputPath);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		List<Person> fullData = fileParser.parseFileContent(linesFromFile);
		return fullData;
	}

	public boolean writeData() {
		List<Person> fullData = getResultsFromInputFile();

		PeopleByMonth peopleByMonth = new PeopleByMonth();
		List<Person> dataForWriting = peopleByMonth.selectNamesFromPersonsInMonth(fullData, month);

		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writeData(outputPath, dataForWriting);

		return true;
	}


}
