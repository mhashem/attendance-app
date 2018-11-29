package com.mhachem.attendance;

import com.mhachem.attendance.shell.AttendanceComponent;
import org.junit.runner.RunWith;
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
