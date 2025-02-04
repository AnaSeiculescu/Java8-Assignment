package org.example;

import java.time.LocalDate;

public class Person {
	private String firstName;
	private String lastName;
	private String birthDate;
//	private LocalDate birthDate;

	public Person(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate='" + birthDate + '\'' +
				'}';
	}
}
