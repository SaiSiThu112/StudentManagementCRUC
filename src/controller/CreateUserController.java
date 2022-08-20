package controller;

import java.io.IOException

;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistant.dao.UserDao;
import persistant.dto.RequestUserDto;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/CreateUserController")
public class CreateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserController() {
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
		
		User userBean = new User();
		
		userBean.setName(request.getParameter("name"));
		userBean.setEmail(request.getParameter("email"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setConfirmPassword(request.getParameter("confirmPassword"));
		userBean.setRole(request.getParameter("role"));
		
		if(userBean.getName().equals("") || userBean.getEmail().equals("") ||
		   userBean.getPassword().equals("") || userBean.getConfirmPassword().equals("") || userBean.getRole().equals("")) {
			request.setAttribute("error","fill all the information !! ");
			request.getRequestDispatcher("add_user.jsp").include(request, response);
			
		}else {
			UserDao dao = new UserDao();
			RequestUserDto dto= new RequestUserDto();
			dto.setName(userBean.getName());
			dto.setEmail(userBean.getEmail());
			dto.setPassword(userBean.getPassword());
			//dto.setConfirmPassword(userBean.getConfirmPassword());		
			dto.setRole(userBean.getRole());
			dao.insertUser(dto);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		}
			
	}

}
