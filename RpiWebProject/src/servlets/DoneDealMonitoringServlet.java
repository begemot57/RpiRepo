package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DoneDealMonitor;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/DoneDealMonitoringServlet")
public class DoneDealMonitoringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DoneDealMonitoringServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DoneDealMonitor monitor = new DoneDealMonitor();
		String stateVarName = "doneDealMonitorAppState";
		if (request.getParameter("start") != null) {
			monitor.start();
			request.setAttribute(stateVarName, "running");
        } else if (request.getParameter("stop") != null) {
        	monitor.stop();
			request.setAttribute(stateVarName, "stopped");
        } else if (request.getParameter("checkstate") != null) {
        	String state = monitor.checkState();
        	request.setAttribute(stateVarName, state);
        }

//        response.sendRedirect("./controllerpage.jsp");
        //request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        request.getRequestDispatcher("controllerpage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
