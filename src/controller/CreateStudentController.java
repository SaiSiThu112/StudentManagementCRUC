package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import persistant.dao.CourseDao;
import persistant.dao.StudentDao;
import persistant.dto.RequestStudentDto;

/**
 * Servlet implementation class CreateStudentController
 */
@WebServlet("/CreateStudentController")
public class CreateStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Student stu = new Student();
        stu.setId(request.getParameter("id"));
        stu.setName(request.getParameter("name"));
        stu.setDob(request.getParameter("dob"));
        stu.setGender(request.getParameter("gender"));
        stu.setPhone(request.getParameter("phone"));
        stu.setEducation(request.getParameter("education"));
        stu.setAttend(request.getParameterValues("course"));
        
        RequestStudentDto dto = new RequestStudentDto();
        dto.setId(stu.getId());
        dto.setName(stu.getName());
        dto.setDob(stu.getDob());
        dto.setGender(stu.getGender());
        dto.setPhone(stu.getPhone());
        dto.setEducation(stu.getEducation());
        dto.setAttend(stu.getAttend());

        StudentDao dao=new StudentDao();
        CourseDao coudao=new CourseDao();
        
        
        int i=dao.insertStudent(dto);
        coudao.InsertCourseDetail(dto);

        if(i==1) {
            request.getRequestDispatcher("student_registration.jsp").forward(request, response);

        }else {
            request.setAttribute("error","Check your database");
            request.getRequestDispatcher("student_registration.jsp").forward(request, response);
        }
		
	}
	
}
