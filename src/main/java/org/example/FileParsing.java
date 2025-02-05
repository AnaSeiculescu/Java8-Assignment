package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileParsing {
	private List<String> lines = new ArrayList<>();
//	private Path path;
	private List<Person> people = new ArrayList<>();

	private void readFile(Path path) throws IOException {
		if(!Files.exists(path)) {
			System.out.println("File not found!");
			System.exit(1);
		}
		try {
			lines = Files.readAllLines(path);
		} catch(IOException e) {
			throw new RuntimeException("Something went wrong reading the file: " + e);
		}
	}

	public List<Person> parseFileContent(Path path) {
		try {
//			readFile(Paths.get("input.csv"));
			readFile(Paths.get(String.valueOf(path)));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		people = lines.stream()
				.map(line -> {
					String[] parts = line.split(",");
					return new Person(parts[0], parts[1], parts[2]);
				}).collect(Collectors.toList());
		return people;
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
