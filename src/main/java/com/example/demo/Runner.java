package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.service.StudentService;

@Component
public class Runner implements ApplicationRunner{
	
	private StudentService studentService;
	
	@Autowired
	public Runner(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Application Started to Run");
		studentService.readCSVFile();
		System.out.println("Data red Successfully and Stored into the database");
	}

}
