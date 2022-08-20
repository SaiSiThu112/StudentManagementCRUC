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
import persistant.dao.UserDao;
import persistant.dto.RequestStudentDto;
import persistant.dto.RequestUserDto;

/**
 * Servlet implementation class UpdateStudentController
 */
@WebServlet("/UpdateStudentController")
public class UpdateStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Student student = new Student();
		student.setId(request.getParameter("id"));	
		student.setName(request.getParameter("name"));
		student.setDob(request.getParameter("dob"));
		student.setGender(request.getParameter("gender"));
		student.setPhone(request.getParameter("phone"));
		student.setEducation(request.getParameter("education"));
		student.setAttend(request.getParameterValues("attend"));
		
		RequestStudentDto dto = new RequestStudentDto();
		dto.setId(student.getId());	
		dto.setName(student.getName());
		dto.setDob(student.getDob());
		dto.setGender(student.getGender());
		dto.setPhone(student.getPhone());
		dto.setEducation(student.getEducation());
		dto.setAttend(student.getAttend());
				
		CourseDao coudao = new CourseDao();
		StudentDao studao = new StudentDao();
		
		studao.updateStudentById(dto);
		coudao.updateCoruse(dto);
		request.getRequestDispatcher("DisplayAttendController").forward(request, response);

	}

}
