package startup;

import javax.servlet.ServletContextEvent;

import servlet.IndexServlet;
import workload.ComponentB;
import workload.ComponentF;
import workload.ComponentA;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		IndexServlet.workload = new ComponentF();
	}
}
