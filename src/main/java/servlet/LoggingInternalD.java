package servlet;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/intD")
public class LoggingInternalD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Queue<String> globalQueue = new ConcurrentLinkedQueue<String>();
	
    public LoggingInternalD() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (String record: globalQueue)
			response.getWriter().append(record + "\n");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
