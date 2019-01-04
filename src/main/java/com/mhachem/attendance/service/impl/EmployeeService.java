package com.mhachem.attendance.service.impl;

import java.util.Optional;

import com.mhachem.attendance.model.Employee;
import com.mhachem.attendance.service.IEmployeeService;

public class EmployeeService implements IEmployeeService {
	
	// todo provide mechanism to get json file and cache employee 
	
	@Override
	public Optional<Employee> findById(int id) {
		return Optional.empty();
	}
	
}
