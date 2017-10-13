package com.dav.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dav.mybatis.model.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentMapper.
 */
@Mapper
public interface StudentMapper {


    /**
     * Gets the all.
     *
     * @param index the index
     * @param keyword the keyword
     * @return the all
     */
    List<Student> getAll(@Param("index") String index,  @Param("keyword") String keyword);

    /**
     * Select student by id.
     *
     * @param id the id
     * @return the student
     */
    Student selectStudentById(int id);

    /**
     * Count record.
     *
     * @param keyword the keyword
     * @return the int
     */
    int countRecord(String keyword);

    /**
     * Update student info.
     *
     * @param student the student
     * @return the int
     */
    int updateStudentInfo(Student student);

    /**
     * Delete student info.
     *
     * @param id the id
     * @return the int
     */
    int deleteStudentInfo(int id);

    /**
     * Insert student info.
     *
     * @param student the student
     * @return the int
     */
    int insertStudentInfo(Student student);
}
