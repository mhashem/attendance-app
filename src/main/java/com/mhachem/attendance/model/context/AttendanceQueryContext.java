package com.mhachem.attendance.model.context;

import java.util.List;

import com.google.common.collect.Lists;

public class AttendanceQueryContext {
	
	private List<Integer> ids = Lists.newArrayList();
	private int month;
	private int year;
	private boolean useDefaults;
	
	public void setIds(List<Integer> ids) {
		this.ids.addAll(ids);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean isUseDefaults() {
		return useDefaults;
	}

	public void setUseDefaults(boolean useDefaults) {
		this.useDefaults = useDefaults;
	}
}
