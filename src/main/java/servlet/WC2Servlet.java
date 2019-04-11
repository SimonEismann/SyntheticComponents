package servlet;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wc2")
public class WC2Servlet extends HttpServlet {
	private Random rand = new Random();
	private static Semaphore sem = new Semaphore(1, false);
	
	private static final long serialVersionUID = 1L;

    public WC2Servlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			performExpWork(0.03 * 1000);
			response.getWriter().append("WC2:80ms");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected double performExpWork(double mean) {
		double lambda = 1 / mean;
		double delay = Math.log(1 - rand.nextDouble()) / (-lambda);
		double result = performConstantWork(delay);
		return result;
	}

	protected double performConstantWork(double milliseconds) {
		try {
			long tic = System.nanoTime();
			sem.acquire();
			long start = System.nanoTime();
			while (true) {
				if (System.nanoTime() - start > milliseconds * 1000000)
					break;
			}
			long toc = System.nanoTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		return 1;
	}
}
