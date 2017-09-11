package com.dav.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dav.mybatis.common.util.Constants;
import com.dav.mybatis.dao.StudentDao;
import com.dav.mybatis.domain.Student;
import com.dav.mybatis.service.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentServiceImpl.
 */
@Service
public class StudentServiceImpl implements StudentService {

    /** The student dao. */
    @Autowired
    private StudentDao studentDao;

    /* (non-Javadoc)
     * @see com.dav.mybatis.service.StudentService#getTotalPage()
     */
    @Override
    public int getTotalPage(String keyword) throws Exception {
		int total = studentDao.getTotalRecord("%" + keyword + "%");
        if(total != -1){
        	total = (int) Math.ceil(total * 1.0 / Constants.CONST_NUM_5);
        }
        return total;
    }

    /* (non-Javadoc)
     * @see com.dav.mybatis.service.StudentService#getStudentWithPage(int)
     */
    @Override
    public List<Student> getStudentWithPage(int page, String keyword) throws Exception {
        List<Student> listResult = studentDao.getStudentWithBeginIndex(page * Constants.CONST_NUM_5, "%" + keyword + "%");
        return listResult;
    }

    /* (non-Javadoc)
     * @see com.dav.mybatis.service.StudentService#getStudentInfo(int)
     */
    @Override
    public Student getStudentInfo(int id) throws Exception{
        Student student = studentDao.getStudentInfo(id);
        return student;
    }

    /* (non-Javadoc)
     * @see com.dav.mybatis.service.StudentService#updateStudent(com.dav.mybatis.domain.Student)
     */
    @Override
    public void updateStudent(Student student) throws Exception {
    	studentDao.updateStudent(student);
    }

    /* (non-Javadoc)
     * @see com.dav.mybatis.service.StudentService#deleteStudent(int)
     */
    @Override
    public void deleteStudent(int id) throws Exception {
       studentDao.deleteStudent(id);
    }

	/* (non-Javadoc)
	 * @see com.dav.mybatis.service.StudentService#insertStudent(com.dav.mybatis.domain.Student)
	 */
	@Override
	public void insertStudent(Student data) throws Exception {
		studentDao.insertStudent(data);
		studentDao.insertStudentInfo(data);
	}

}
