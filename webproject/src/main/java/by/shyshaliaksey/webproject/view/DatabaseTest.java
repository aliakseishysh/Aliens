package by.shyshaliaksey.webproject.view;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import by.shyshaliaksey.webproject.model.dao.MysqlController;
import by.shyshaliaksey.webproject.model.entity.Alien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseTest
 */
public class DatabaseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DatabaseTest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MysqlController mysqlController = new MysqlController();
		List<Alien> aliens = mysqlController.getAll();
		String aliensString = aliens.stream().map(Alien::toString).collect(Collectors.joining("</br>"));
		response.getWriter().append(aliensString);
		mysqlController.releaseConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
