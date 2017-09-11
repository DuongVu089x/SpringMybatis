package com.dav.mybatis.dao;

import java.util.List;

import com.dav.mybatis.model.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentDao.
 */
public interface StudentDao {

	/**
	 * Gets the total record.
	 *
	 * @param keyword the keyword
	 * @return the total record
	 * @throws Exception the exception
	 */
	int getTotalRecord(String keyword) throws Exception;

	/**
	 * Gets the student with begin index.
	 *
	 * @param beginIndex the begin index
	 * @param keyword the keyword
	 * @return the student with begin index
	 * @throws Exception the exception
	 */
	List<Student> getStudentWithBeginIndex(int beginIndex, String keyword) throws Exception;

	 /**
 	 * Gets the student info.
 	 *
 	 * @param id the id
 	 * @return the student info
 	 * @throws Exception the exception
 	 */
 	Student getStudentInfo(int id) throws Exception;

	 /**
 	 * Update student.
 	 *
 	 * @param student the student
 	 * @throws Exception the exception
 	 */
 	void updateStudent(Student student) throws Exception;

	 /**
 	 * Delete student.
 	 *
 	 * @param id the id
 	 * @throws Exception the exception
 	 */
 	void deleteStudent(int id) throws Exception;

	 /**
 	 * Insert student.
 	 *
 	 * @param data the data
 	 * @throws Exception the exception
 	 */
 	void insertStudent(Student data) throws Exception;

	 /**
 	 * Insert student info.
 	 *
 	 * @param data the data
 	 * @throws Exception the exception
 	 */
 	void insertStudentInfo(Student data) throws Exception;
}
