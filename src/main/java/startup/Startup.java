package startup;

import javax.servlet.ServletContextEvent;

import servlet.IndexServlet;
import workload.ComponentB;
import workload.ComponentC;
import workload.ComponentD;
import workload.ComponentE;
import workload.ComponentF;
import workload.ComponentG;
import workload.ComponentA;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		String component = System.getenv("Component");
		if (component.matches("A")) {
			IndexServlet.workload = new ComponentA(System.getenv("IP_B"));
		}
		if (component.matches("B"))
			IndexServlet.workload = new ComponentB(System.getenv("IP_C"), System.getenv("IP_E"));
		if (component.matches("C"))
			IndexServlet.workload = new ComponentC(System.getenv("IP_D"));
		if (component.matches("D"))
			IndexServlet.workload = new ComponentD();
		if (component.matches("E"))
			IndexServlet.workload = new ComponentE(System.getenv("IP_F"), System.getenv("IP_G"));
		if (component.matches("F"))
			IndexServlet.workload = new ComponentF();
		if (component.matches("G"))
			IndexServlet.workload = new ComponentG();
	}
}
