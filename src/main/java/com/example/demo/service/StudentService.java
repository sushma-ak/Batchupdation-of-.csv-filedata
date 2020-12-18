package com.example.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

@Service
public class StudentService {
	private StudentDao studentdao;

	@Autowired
	public StudentService(StudentDao studentdao) {
		this.studentdao = studentdao;
	}

	public void readCSVFile()
	{
		String filepath="C:/Users/annap/Downloads/student.csv";
		try {
			BufferedReader reader=new BufferedReader(new FileReader(filepath));
			CSVParser records=CSVParser.parse(reader, CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			ArrayList<Student> studentList=new ArrayList<Student>();
			
			for(CSVRecord record:records)
			{
				Student student=new Student();
				student.setStudentId(Integer.parseInt(record.get(0)));
				student.setStudentName(record.get(1));
				studentList.add(student);			
			}
			int[] output=studentdao.batchUpdation(studentList);
			System.out.println(output.length+ "rows of the stored");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
