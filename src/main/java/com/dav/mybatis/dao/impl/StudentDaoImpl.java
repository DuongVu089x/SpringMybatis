package com.dav.mybatis.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dav.mybatis.dao.StudentDao;
import com.dav.mybatis.domain.Student;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentDaoImpl.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

	/** The sql session factory. */
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#getTotalRecord(java.lang.String)
	 */
	@Override
	public int getTotalRecord(String keyword) throws Exception {
		SqlSession sqlSession = null;
		int total = -1;
		try {
			sqlSession = sqlSessionFactory.openSession();
			total = sqlSession.selectOne("countRecord", keyword);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return total;
	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#getStudentWithBeginIndex(int, java.lang.String)
	 */
	@Override
	public List<Student> getStudentWithBeginIndex(int beginIndex, String keyword) throws Exception {
		SqlSession sqlSession = null;
		List<Student> listResult = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			HashMap<String, Object> parameter = new HashMap<>();
			parameter.put("index", beginIndex);
			parameter.put("keyword", keyword);

			listResult = sqlSession.selectList("selectAll", parameter);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

		return listResult;
	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#getStudentInfo(int)
	 */
	@Override
	public Student getStudentInfo(int id) throws Exception {
		SqlSession sqlSession = null;
		Student student = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			student = sqlSession.selectOne("selectStudentById", id);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return student;
	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#updateStudent(com.dav.mybatis.domain.Student)
	 */
	@Override
	public void updateStudent(Student student) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.update("updateStudentInfo", student);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#deleteStudent(int)
	 */
	@Override
	public void deleteStudent(int id) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete("deleteStudentInfo", id);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#insertStudent(com.dav.mybatis.domain.Student)
	 */
	@Override
	public void insertStudent(Student data) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("insertStudent", data);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.StudentDao#insertStudentInfo(com.dav.mybatis.domain.Student)
	 */
	@Override
	public void insertStudentInfo(Student data) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("insertStudentInfo", data);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
