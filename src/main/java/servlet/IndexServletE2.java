package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import workload.Workload;


@WebServlet("/indexE2")
public class IndexServletE2 extends HttpServlet {
	
	public static Workload workload = null;
	private static final long serialVersionUID = 1L;

    public IndexServletE2() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description;
		try {
			description = workload.performWork();
			response.getWriter().append(description);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}