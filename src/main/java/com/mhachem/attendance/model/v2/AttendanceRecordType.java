package com.mhachem.attendance.model.v2;

public enum  AttendanceRecordType {

	IN("IN"), OUT("OUT");

	String type;

	AttendanceRecordType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
