package com.mhachem.attendance;

import java.io.IOException;
import java.io.InputStream;

import com.mhachem.attendance.config.AttendanceConfig;
import com.mhachem.attendance.model.AttendanceResult;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AttendanceComponentIT {

	private AttendanceComponent attendanceComponent;

/*
	@Before
	public void setup() {
		attendanceComponent = new AttendanceComponent(new AttendanceConfig());
	}

	@Test
	public void testComputations() throws IOException {
		try (InputStream inputStream = new DefaultResourceLoader().getResource("classpath:/102518-27.xls")
			.getInputStream()) {
			AttendanceResult attendanceResult = attendanceComponent.computeAttendance(inputStream, true);
			Assertions.assertThat(attendanceResult.getComputedDays()).isEqualTo(19);
		}
	}
*/

}
