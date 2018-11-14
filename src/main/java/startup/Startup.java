package startup;

import javax.servlet.ServletContextEvent;

import servlet.IndexServlet;
import workload.ConstantWorkload;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		IndexServlet.workload = new ConstantWorkload(3);
	}
}
