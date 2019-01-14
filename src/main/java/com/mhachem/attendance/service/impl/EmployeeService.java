package com.mhachem.attendance.service.impl;

import java.util.Optional;

import com.mhachem.attendance.dao.IEmployeeDao;
import com.mhachem.attendance.model.Employee;
import com.mhachem.attendance.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
	
	private final IEmployeeDao employeeDao;

	@Autowired
	public EmployeeService(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Optional<Employee> findById(int id) {
		return employeeDao.findById(id);
	}
	
}
