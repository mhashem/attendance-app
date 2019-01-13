package com.mhachem.attendance.simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import spark.Spark;

public class AttendanceMachineSimulator {

	private static byte[] excelFileBytes;

	public static void main(String[] args) throws IOException {

		excelFileBytes = Files.readAllBytes(
			Paths.get("C:/Users/mahmoud/Documents/Projects/attendance-app/src/test/resources/102518-27.xls"));

		Spark.port(9001);
		Spark.get("/if.cgi", (request, response) -> {
			response.header("Content-Type", "application/vnd.ms-excel");
			response.header("Content-Disposition", "attachment; filename=\"attendance.xls\"");

			response.raw().getOutputStream().write(excelFileBytes);
			response.raw().getOutputStream().flush();
			response.raw().getOutputStream().close();
			return response.raw();
		});
	}

}
