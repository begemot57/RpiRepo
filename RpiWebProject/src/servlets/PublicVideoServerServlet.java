package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CamAndMjpgStreamer;
import beans.CarOld;


/**
 * Servlet implementation class Servlet
 */
@WebServlet("/PublicVideoServerServlet")
public class PublicVideoServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PublicVideoServerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CamAndMjpgStreamer streamer = new CamAndMjpgStreamer();
		String stateVarName = "videoStreamAppState";
		if (request.getParameter("start") != null) {
			streamer.start();
			request.setAttribute(stateVarName, "running");
        } else if (request.getParameter("stop") != null) {
        	streamer.stop();
        	request.setAttribute(stateVarName, "stopped");
        } else if (request.getParameter("checkstate") != null) {
        	String state = streamer.checkState();
        	request.setAttribute(stateVarName, state);
        }
		
//        response.sendRedirect("./controllerpage.jsp");
		request.getRequestDispatcher("videoserver.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
