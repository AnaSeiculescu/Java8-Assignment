package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParsing {
	private List<String> lines = new ArrayList<>();
//	private Path path;
	private List<Person> people = new ArrayList<>();

	private void readFile(Path path) throws IOException {
		try {
			lines = Files.readAllLines(path);
		} catch(IOException e) {
			throw new RuntimeException("Something went wrong reading the file: " + e);
		}
	}

	public List<String> parseFileContent() {
		try {
			readFile(Paths.get("src/main/personsData.csv"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lines;
	}

//	public void parseFile() {
//		try {
//			Stream<String> lines = Files.lines(Path.of("personsData.csv"));
//			people = lines.map(line -> {
//				String[] parts = line.split(",");
//				return new Person(parts[0], parts[1], parts[2]);
//			}).collect(Collectors.toList());
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//
//	}

	public List<Person> getPeople() {
		return people;
	}

	@Override
	public String toString() {
		return "FileParsing{" +
				"lines=" + lines +
				'}';
	}
}
