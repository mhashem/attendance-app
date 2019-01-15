package com.mhachem.attendance.model.context;

import java.util.Collections;
import java.util.List;

public class AttendanceQueryContext {

	private List<Integer> ids;
	
	private int month;
	private int year;
	private boolean useDefaults;

	public AttendanceQueryContext(List<Integer> ids, int month, int year, boolean useDefaults) {
		this.ids = ids;
		this.month = month;
		this.year = year;
		this.useDefaults = useDefaults;
	}

	public List<Integer> getIds() {
		return Collections.unmodifiableList(ids);
	}

	public int getMonth() {
		return month;
	}


	public int getYear() {
		return year;
	}

	
	public boolean isUseDefaults() {
		return useDefaults;
	}

}
