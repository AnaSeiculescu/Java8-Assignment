package org.example;

import org.junit.Test;
import org.mockito.MockedStatic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FileParserTest {

	@Test
	public void parseFileContent_returnsAListOfPeople() {
		Path inputPath = Paths.get("/folder/file.ext");

		try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
			mockedFiles.when(() -> Files.exists(inputPath)).thenReturn(true);
			mockedFiles.when(() -> Files.readAllLines(inputPath))
					.thenReturn(List.of("Gigel,Popescu,1988-06-16"));

			// prepare
			List<Person> expectedResult = new ArrayList<>();

			Person person1 = new Person("Gigel", "Popescu", LocalDate.parse("1988-06-16"));
			expectedResult.add(person1);

			// execute
			FileParser parser = new FileParser();
			List<Person> actualResult = parser.parseFileContent(inputPath);

			// verify
			 assertEquals(expectedResult.toString(), actualResult.toString());
		}
	}
}