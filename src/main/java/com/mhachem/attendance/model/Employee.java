package com.mhachem.attendance.model;

import java.util.Objects;

public class Employee {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
