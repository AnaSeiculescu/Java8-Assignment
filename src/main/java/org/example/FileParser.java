package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileParser {
	private List<String> lines = new ArrayList<>();
	private List<Person> people = new ArrayList<>();

	public void readFile(Path path) {
		if (!Files.exists(path)) {
			System.out.println("File not found!");
			System.exit(1);
		}
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong reading the file: " + e);
		}
	}

	public List<Person> parseFileContent() {

		List<String> invalidEntries = new ArrayList<>();

		if (lines.isEmpty()) {
			System.out.println("File is empty or has invalid entries.");
		}

		people = lines.stream()
				.map(line -> {
					Optional<Person> person = parseLine(line);
					return person;
				}).filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		invalidEntries = lines.stream()
				.filter(line -> parseLine(line).isEmpty())
				.collect(Collectors.toList());

		return people;
	}

	private Optional<Person> parseLine(String line) {
		String[] parts = line.split(",");

		if (line.length() < 3) return Optional.empty();

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
			System.out.println("Invalid birth date format at line: " + line);
			return Optional.empty();
		}
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "FileParser{" +
				"people=" + people +
				'}';
	}
}
