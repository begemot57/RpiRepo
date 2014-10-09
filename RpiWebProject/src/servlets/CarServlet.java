package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Car;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CarServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Car car = new Car();

        if (request.getParameter("forward") != null) {
            car.goForward();
        } else if (request.getParameter("back") != null) {
        	car.goBack();
        } else if (request.getParameter("right") != null) {
        	car.goRight();
        } else if (request.getParameter("left") != null) {
        	car.goLeft();
        } else if (request.getParameter("stop") != null){
        	car.stop();
        } else if (request.getParameter("speed_minus") != null){
        	car.goSlower();
        } else if (request.getParameter("speed_plus") != null){
        	car.goFaster();
        } else if (request.getParameter("off") != null){
        	car.off();
        }

        response.sendRedirect("./carcontrolbuttons.jsp");
        //request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Car car = new Car();

        if (request.getParameter("forward") != null) {
            car.goForward();
        } else if (request.getParameter("back") != null) {
        	car.goBack();
        } else if (request.getParameter("right") != null) {
        	car.goRight();
        } else if (request.getParameter("left") != null) {
        	car.goLeft();
        } else if (request.getParameter("stop") != null){
        	car.stop();
        } else if (request.getParameter("off") != null){
        	car.off();
        }

        response.sendRedirect("./carcontrolmotion.jsp");
	}

}
