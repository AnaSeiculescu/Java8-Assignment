package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileParsing {
	private List<String> lines = new ArrayList<>();
	private List<Person> people = new ArrayList<>();
	private Path path;

	public FileParsing(Path path) {
		this.path = path;
	}

	private void readFile(Path path) throws IOException {
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

	public List<Person> parseFileContent(Path path) {
		try {
			readFile(Paths.get(String.valueOf(path)));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		List<String> invalidEntries = new ArrayList<>();

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

	@Override
	public String toString() {
		return "FileParsing{" +
				"people=" + people +
				'}';
	}
}
