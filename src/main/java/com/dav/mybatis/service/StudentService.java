package com.dav.mybatis.service;

import java.util.List;

import com.dav.mybatis.domain.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentService.
 */
public interface StudentService {

    /**
     * Gets the total page.
     *
     * @param keyword the keyword
     * @return the total page
     * @throws Exception the exception
     */
    int getTotalPage(String keyword) throws Exception;

    /**
     * Gets the student with page.
     *
     * @param page the page
     * @param keyword the keyword
     * @return the student with page
     * @throws Exception the exception
     */
    List<Student> getStudentWithPage(int page, String keyword) throws Exception;

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
}
