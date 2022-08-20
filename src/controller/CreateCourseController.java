package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import persistant.dao.CourseDao;
import persistant.dto.RequestCourseDto;
import persistant.dto.ResponseCourseDto;

/**
 * Servlet implementation class CreateCourseController
 */
@WebServlet("/CreateCourseController")
public class CreateCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCourseController() {
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
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		Course course = new Course();
		course.setId(id);
		course.setName(name);
		
		if(course.getId().equals("")||course.getName().equals("")) {
			request.setAttribute("error", "Enter Course Name!");
			request.getRequestDispatcher("course_registration.jsp").forward(request, response);
		}
		
		CourseDao dao = new CourseDao();
		List<ResponseCourseDto> list = dao.getAllCourse();
		for(ResponseCourseDto res: list) {
			if(res.getName().equals(course.getName())) {
				request.setAttribute("error","Already Exit");
				request.getRequestDispatcher("course_registration.jsp").forward(request, response);
			}
		}
		
		RequestCourseDto dto = new RequestCourseDto();
		dto.setId(course.getId());
		dto.setName(course.getName());
		dao.insertCourse(dto);
		request.getSession().setAttribute("count","COU"+String.valueOf(Integer.parseInt(dto.getId().substring(3))+1));
		request.getServletContext().setAttribute("clist", list);
		request.getRequestDispatcher("course_registration.jsp").forward(request, response);
	}

}
