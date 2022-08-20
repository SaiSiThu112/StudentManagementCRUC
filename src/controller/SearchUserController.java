package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class SearchUserController
 */
@WebServlet("/SearchUserController")
public class SearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  	User user = new User();

	        if(request.getParameter("id").equals("")) {
	            user.setId("USR0");
	        }else {
	            user.setId(request.getParameter("id"));
	        }

	        user.setName(request.getParameter("name"));

	        RequestUserDto req = new RequestUserDto();
	        req.setId(user.getId().substring(3));
	        req.setName(user.getName());


	        UserDao dao = new UserDao();

	        if(dao.specificUser(req).isEmpty()) {
	            request.setAttribute("notfound","not found!!");
	            request.getRequestDispatcher("user.jsp").include(request, response);
	        }
	        else {

	            request.setAttribute("userApp",dao.specificUser(req));

	            List<ResponseUserDto> a = dao.specificUser(req);


	            request.getRequestDispatcher("user.jsp").include(request, response);
	        }
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
