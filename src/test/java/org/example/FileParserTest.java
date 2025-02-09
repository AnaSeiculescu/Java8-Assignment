package org.example;

import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FileParserTest {

	@Test
	public void parseFileContentOneEntryHappyPath() {

		// prepare
		List<Person> forExpectedResult = new ArrayList<>();
		Person person1 = new Person("Gigel", "Popescu", LocalDate.parse("1988-06-16"));
		forExpectedResult.add(person1);
		String expectedResult = forExpectedResult.stream()
				.map(Person::toStringForTest)
				.collect(Collectors.joining("\n"));

		List<String> lines = List.of("Gigel,Popescu,1988-06-16");

		// execute
		FileParser parser = new FileParser();
		parser.setLines(lines);
		List<Person> forActualResult = parser.parseFileContent();
		String actualResult = forActualResult.stream()
				.map(Person::toStringForTest)
						.collect(Collectors.joining("\n"));

		// verify
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void parseFileContentTwoEntriesHappyPath() {

		// prepare
		List<Person> forExpectedResult = new ArrayList<>();
		Person person1 = new Person("Gigel", "Popescu", LocalDate.parse("1988-06-16"));
		Person person2 = new Person("Maria", "Zaharia", LocalDate.parse("1995-08-21"));
		forExpectedResult.add(person1);
		forExpectedResult.add(person2);
		String expectedResult = forExpectedResult.stream()
				.map(Person::toStringForTest)
				.collect(Collectors.joining("\n"));

		List<String> lines = List.of("Gigel,Popescu,1988-06-16", "Maria,Zaharia,1995-08-21");

		// execute
		FileParser parser = new FileParser();
		parser.setLines(lines);
		List<Person> forActualResult = parser.parseFileContent();
		String actualResult = forActualResult.stream()
				.map(Person::toStringForTest)
				.collect(Collectors.joining("\n"));

		// verify
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void parseFileContentNoInput() {

		// prepare
		List<Person> forExpectedResult = new ArrayList<>();
		String expectedResult = "";

		List<String> lines = List.of();

		// execute
		FileParser parser = new FileParser();
		parser.setLines(lines);
		List<Person> forActualResult = parser.parseFileContent();
		String actualResult = forActualResult.stream()
				.map(Person::toStringForTest)
				.collect(Collectors.joining("\n"));

		// verify
		assertEquals(expectedResult, actualResult);

	}
}