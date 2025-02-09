package org.example;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PeopleByMonthTest {

	@Test
	public void selectNamesFromPersonsByMonthHappyPath() {
		// prepare
		PeopleByMonth peopleByMonth = new PeopleByMonth();
		List<Person> inputData = new ArrayList<>();
		int month = 6;
		Person person1 = new Person("Gigel", "Popescu", LocalDate.parse("1988-06-16"));
		Person person2 = new Person("Maria", "Zaharia", LocalDate.parse("1995-08-21"));
		inputData.add(person1);
		inputData.add(person2);

		String expectedResult = "[Gigel,Popescu]";

		// execute
		String actualResult = peopleByMonth.selectNamesFromPersonsByMonth(inputData, month).toString();

		//verify
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void selectNamesFromPersonsByMonthNoInput() {
		// prepare
		PeopleByMonth peopleByMonth = new PeopleByMonth();
		List<Person> inputData = new ArrayList<>();
		int month = 6;

		String expectedResult = "[]";

		// execute
		String actualResult = peopleByMonth.selectNamesFromPersonsByMonth(inputData, month).toString();

		//verify
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void selectNamesFromPersonsByMonthWrongInput() {
		// prepare
		PeopleByMonth peopleByMonth = new PeopleByMonth();
		List<Person> inputData = new ArrayList<>();
		int month = 6;
		Person person1 = new Person("Gigel", "Popescu", LocalDate.parse("1988-04-16"));
		Person person2 = new Person("Maria", "Zaharia", LocalDate.parse("1995-08-21"));
		inputData.add(person1);
		inputData.add(person2);

		String expectedResult = "[]";

		// execute
		String actualResult = peopleByMonth.selectNamesFromPersonsByMonth(inputData, month).toString();

		//verify
		assertEquals(expectedResult, actualResult);
		// There is no person with birth month ...
	}
}