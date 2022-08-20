package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistant.dao.UserDao;
import persistant.dto.RequestUserDto;
import persistant.dto.ResponseUserDto;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/LogInController")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInController() {
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
		
		User user = new User();
        user.setId(request.getParameter("id"));
        user.setPassword(request.getParameter("password"));

        if(user.getId().equals("") || user.getPassword().equals("")) {
            request.setAttribute("error","Fields cannot be blank!");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }else {

            RequestUserDto req = new RequestUserDto();
            req.setId(user.getId().substring(3));
            req.setPassword(user.getPassword());

            UserDao dao = new UserDao();
            ResponseUserDto res = dao.selectOne(req);

            if(res == null) {
                request.setAttribute("error","No Data!");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }else {
            	request.getSession().setAttribute("userApp",dao.getAllUser());
                request.getSession().setAttribute("userName", res);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
    }
}
	


}
