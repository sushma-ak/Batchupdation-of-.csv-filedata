package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.example.demo.model.Student;

@Repository
public class StudentDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private static String SAVE_BATCH_STUDENT_DATA="INSERT INTO STUDENT(ID,NAME) VALUES(?,?)";
	
	
	@Autowired
	public StudentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int[] batchUpdation(List<Student> studentlist)
	{
		return this.jdbcTemplate.batchUpdate(SAVE_BATCH_STUDENT_DATA, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				 ps.setInt(1,studentlist.get(i).getStudentId());
				 ps.setString(2, studentlist.get(i).getStudentName());
			}
			@Override
			public int getBatchSize() {
				return studentlist.size();
			}
			
		});
	}
	

}
