package com.mhachem.attendance.dao;

import java.util.Optional;

import com.mhachem.attendance.model.Employee;

public interface IEmployeeDao {
	
	Optional<Employee> findById(int id);
	
}
