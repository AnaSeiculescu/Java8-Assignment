package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileParser {

	public List<String> readFile(Path path) {
		List<String> lines;
		if (!Files.exists(path)) {
			System.out.println("File not found!");
			System.exit(1);
		}
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong reading the file: " + e);
		}
		return lines;
	}

	/**
	 * Parse file content list.
	 *
	 * @param linesFromFile the lines from file
	 * @return a list of Person from the correct entries.
	 * Takes a list of strings representing the valid lines read from the file.
	 * The lines are validated in the parseLine() private method.
	 */
	public List<Person> parseFileContent(List<String> linesFromFile) {

		List<Person> people;

		if (linesFromFile.isEmpty()) {
			System.out.println("File is empty or has invalid entries.");
		}

		people = linesFromFile.stream()
				.map(line -> {
					Optional<Person> person = parseLine(line);
					return person;
				}).filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		return people;
	}

	private Optional<Person> parseLine(String line) {
		String[] parts = line.split(",");

		if (parts.length < 3) {
			System.out.println("Found less than three elements at line: " + line);
			return Optional.empty();
		}

		for (String element : parts) {
			if (element.trim().isEmpty()) {
				System.out.println("Entry is incomplete at line: " + line);
				return Optional.empty();
			}
		}

		try {
			LocalDate birthDate = LocalDate.parse(parts[2]);
			return Optional.of(new Person(parts[0], parts[1], birthDate));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid birthdate format at line: " + line);
			return Optional.empty();
		}
	}

}
