package com.mhachem.attendance.model;

import java.util.Objects;

public class Employee {

	private static final String UNKNOWN = "unknown";
	
	private int id;
	private String name;

	private Employee(int id) {
		this.id = id;
		this.name = UNKNOWN;
	}
	
	private Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Employee from(int id) {
		return new Employee(id);
	}
	
	public static Employee from(int id, String name) {
		return new Employee(id, name);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return getId() == employee.getId() && Objects.equals(getName(), employee.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
}
