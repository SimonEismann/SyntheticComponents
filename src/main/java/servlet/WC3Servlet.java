package servlet;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wc3")
public class WC3Servlet extends HttpServlet {
	private Random rand = new Random();
	private static Semaphore sem = new Semaphore(1, false);
	
	private static final long serialVersionUID = 1L;

    public WC3Servlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			performNormalWork(0.010 * 1000, 0.002 * 1000);
			response.getWriter().append("WC3:10ms+-2ms");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected double performNormalWork(double mean, double std) {
		double result = performConstantWork(rand.nextGaussian() * std + mean);
		return result;
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		return 1;
	}
}