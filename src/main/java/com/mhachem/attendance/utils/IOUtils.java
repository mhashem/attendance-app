package com.mhachem.attendance.utils;

public class IOUtils {

	// todo add onError, onComplete ... or fallback to use RxJava
	public interface ProgressListener {
		void onProgress(long value);
	}
	
}
