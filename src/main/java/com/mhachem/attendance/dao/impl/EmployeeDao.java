package com.mhachem.attendance.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mhachem.attendance.dao.IEmployeeDao;
import com.mhachem.attendance.model.Employee;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao implements IEmployeeDao {

	private Map<Integer, Employee> employeeMap;

	public EmployeeDao() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		employeeMap = Lists.newArrayList(objectMapper
			.readValue(new DefaultResourceLoader().getResource("employee.json").getInputStream(), Employee[].class))
			.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
	}

	@Override
	public Optional<Employee> findById(int id) {
		return Optional.ofNullable(employeeMap.get(id));
	}

}
