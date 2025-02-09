package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeopleByMonth {
	public List<Person> selectNamesFromPersonsByMonth(List<Person> fullData, int month) {
		List<Person> dataForWriting = new ArrayList<>();
		if (fullData.isEmpty()) {
			System.out.println("There is no data from the file, the list of persons is empty.");
			return dataForWriting;
		}

		boolean monthExists = false;
		for (Person person : fullData) {
			if (person.getBirthDate().getMonthValue() == month) {
				monthExists = true;
			}
		}

		if (!monthExists) {
			System.out.println("There is no person with birth month: " + month);
			return dataForWriting;
		}

		dataForWriting = fullData.stream()
				.filter(person -> person.getBirthDate().getMonthValue() == month)
				.map(person -> new Person(person.getFirstName(), person.getLastName()))
				.collect(Collectors.toList());

		return dataForWriting;
	}
}
