package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeopleByMonth {
	/**
	 * Select names from persons by month list.
	 *
	 * @param fullData the full data
	 * @param month    the month
	 * @return a list of people.
	 * Takes the full list of collected persons (with first name, last name and birthdate) and an integer for birth month.
	 * Selects the persons after the birth month and returns a list of persons with just first name and last name.
	 */
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
