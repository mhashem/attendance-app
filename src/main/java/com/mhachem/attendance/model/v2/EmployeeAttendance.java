package com.mhachem.attendance.model.v2;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class EmployeeAttendance {

	private int cardId;
	private int employeeId;
	private String name;
	private List<AttendanceRecord> attendanceRecords;

}
