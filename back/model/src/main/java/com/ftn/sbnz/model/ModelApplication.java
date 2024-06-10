package com.ftn.sbnz.model;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class ModelApplication implements ApplicationRunner {

	public static void main(String[] args) {
		generateSqlInsertStatementsFourValues(4, 106, "mobile_packages");
		generateSqlInsertStatementsTwoValues(107, 124, "cable_packages");
		generateSqlInsertStatementsThreeValues(125, 159, "internet_packages");
//		SpringApplication.run(ModelApplication.class, args);
		System.out.println("Hello from model!");
		ApplicationContext ctx = SpringApplication.run(ModelApplication.class, args);
	}

	public static void generateSqlInsertStatementsFourValues(int startIndex, int endIndex, String table) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO " + table + " (id, minutes, internet, expiration) VALUES\n");

		for (int i = startIndex; i <= endIndex; i++) {
			sqlBuilder.append("(").append(i).append(", 0, 0, 0)");

			if (i != endIndex) {
				sqlBuilder.append(",\n");
			} else {
				sqlBuilder.append(";\n");
			}
		}
		writeInFile(sqlBuilder);
	}

	public static void generateSqlInsertStatementsThreeValues(int startIndex, int endIndex, String table) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO " + table + " (id, internet, expiration) VALUES\n");

		for (int i = startIndex; i <= endIndex; i++) {
			sqlBuilder.append("(").append(i).append(", 0, 0)");

			if (i != endIndex) {
				sqlBuilder.append(",\n");
			} else {
				sqlBuilder.append(";\n");
			}
		}
		writeInFile(sqlBuilder);
	}

	public static void generateSqlInsertStatementsTwoValues(int startIndex, int endIndex, String table) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO " + table + " (id, chanel_number) VALUES\n");

		for (int i = startIndex; i <= endIndex; i++) {
			sqlBuilder.append("(").append(i).append(", 0)");

			if (i != endIndex) {
				sqlBuilder.append(",\n");
			} else {
				sqlBuilder.append(";\n");
			}
		}
		writeInFile(sqlBuilder);
	}

	private static void writeInFile(StringBuilder sqlBuilder) {
		String path = String.valueOf(ModelApplication.class.getResource("/data.sql")).replace("file:/", "").replace("%20", " ");
		System.out.println(path);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
			writer.write(sqlBuilder.toString());
//			System.out.println("SQL insert statements have been written to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
