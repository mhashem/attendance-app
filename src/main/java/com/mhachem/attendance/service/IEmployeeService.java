package com.mhachem.attendance.service;

import java.util.Optional;

import com.mhachem.attendance.model.Employee;

public interface IEmployeeService {
	
	Optional<Employee> findById(int id);
	
}
