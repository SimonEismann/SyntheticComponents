package startup;

import javax.servlet.ServletContextEvent;

import servlet.IndexServlet;
import workload.ConstantWorkload;
import workload.ExpWorkload;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		IndexServlet.workload = new ExpWorkload(3);
	}
}