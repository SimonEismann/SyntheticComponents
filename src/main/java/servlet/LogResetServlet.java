package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import workload.Workload;


@WebServlet("/reset")
public class LogResetServlet extends HttpServlet {
	
	public static Workload workload = null;
	private static final long serialVersionUID = 1L;

    public LogResetServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoggingExtCall1.globalQueue.clear();
		LoggingExtCall2.globalQueue.clear();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}