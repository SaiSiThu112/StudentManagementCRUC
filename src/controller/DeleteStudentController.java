package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistant.dao.CourseDao;
import persistant.dao.StudentDao;
import persistant.dto.RequestStudentDto;
import persistant.dto.StudentAttendCourse;

/**
 * Servlet implementation class DeleteStudentController
 */
@WebServlet("/DeleteStudentController")
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id").substring(3);
		RequestStudentDto dto = new RequestStudentDto();
		StudentAttendCourse stuAttend = new StudentAttendCourse();
		
		dto.setId(id);
		stuAttend.setStudentid(id);
		StudentDao stuDao = new StudentDao();
		CourseDao couDao = new CourseDao();
		stuDao.deleteStudentById(id);
		int i = couDao.deleteCourse(stuAttend);
		if(i==0) {
			request.getRequestDispatcher("DisplayStudentController").forward(request, response);
		}
		request.getRequestDispatcher("DisplayStudentController").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
