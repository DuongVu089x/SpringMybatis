package com.dav.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dav.mybatis.domain.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentMapper.
 */
@Mapper
public interface StudentMapper {

  
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
    
    int insertStudentInfo(Student student);
}
