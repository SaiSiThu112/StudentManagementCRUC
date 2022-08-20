package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistant.dao.UserDao;
import persistant.dto.RequestUserDto;

/**
 * Servlet implementation class UpdateUserController
 */
@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserController() {
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
		
		RequestUserDto dto = new RequestUserDto();
		dto.setId(request.getParameter("id").substring(3));	
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setPassword(request.getParameter("password"));
		//dto.setConfirmPassword(request.getParameter("confirmPassword"));
		dto.setRole(request.getParameter("role"));
		
		
		if(dto.getName().equals("")||dto.getEmail().equals("")||dto.getPassword().equals("")
		   ||request.getParameter("confirmPassword").equals("")) {
			request.setAttribute("error", "Fields cannot be blank!");
			request.setAttribute("bean", dto);
			request.getRequestDispatcher("add_user.jsp").forward(request, response);		
			}
		if(!dto.getPassword().equals(request.getParameter("confirmPassword"))){
			request.setAttribute("error", "Password does not match!");
			request.getRequestDispatcher("add_user.jsp").forward(request, response);
			}
		else {
			UserDao dao = new UserDao();
			dao.updateUserById(dto);
			request.getSession().setAttribute("userApp",dao.getAllUser());
			request.getRequestDispatcher("user.jsp").forward(request, response);
			}

		
	}

}
