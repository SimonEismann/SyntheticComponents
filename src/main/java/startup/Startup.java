package startup;

import javax.servlet.ServletContextEvent;

import servlet.IndexServlet;
import workload.ComponentB;
import workload.ComponentF;
import workload.ComponentA;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		String component = System.getenv("Component");
		if (component.matches("A")) {
			IndexServlet.workload = new ComponentA(System.getenv("IP_B"));
		}
		if (component.matches("B"))
			IndexServlet.workload = new ComponentB();
		if (component.matches("F"))
			IndexServlet.workload = new ComponentF();
	}
}
