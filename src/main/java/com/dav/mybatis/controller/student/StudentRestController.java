package com.dav.mybatis.controller.student;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dav.mybatis.domain.Student;
import com.dav.mybatis.service.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentRestController.
 */
@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    /** The student service. */
    @Autowired
    private StudentService studentService;

    /**
     * Gets the total page.
     *
     * @param keyword the keyword
     * @return the total page
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getTotalPage", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public int getTotalPage(@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword) throws Exception {
        return studentService.getTotalPage(keyword);
    }

	/**
	 * Gets the student with page.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the student with page
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/getStudentWithPage", method = RequestMethod.GET)
	public List<Student> getStudentWithPage(@RequestParam(required = true, defaultValue = "1", value = "page") int page,
			@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword) throws Exception {
		return studentService.getStudentWithPage(page, keyword);
	}

    /**
     * Gets the student info.
     *
     * @param id the id
     * @return the student info
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET)
    public Student getStudentInfo(@RequestParam(required = true, defaultValue = "1", value = "id") int id) throws Exception {
        return studentService.getStudentInfo(id);
    }

    /**
     * Insert student.
     *
     * @param data the data
     * @throws Exception the exception
     */
    @RequestMapping(value = "/insertStudent", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void insertStudent(@RequestBody @Valid Student data) throws Exception {
        studentService.insertStudent(data);
    }

    /**
     * Update student.
     *
     * @param id the id
     * @param data the data
     * @throws Exception the exception
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void updateStudent(@RequestParam(required = true, defaultValue = "1", value = "id") int id, @RequestBody @Valid Student data) throws Exception {
    	studentService.updateStudent(data);

    }

    /**
     * Delete student.
     *
     * @param id the id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void deleteStudent(@RequestParam(required = true, defaultValue = "0", value = "id") int id) throws Exception {
        studentService.deleteStudent(id);
    }

}
