package startup;

import java.util.concurrent.Semaphore;

import javax.servlet.ServletContextEvent;

import servlet.IndexServletA;
import servlet.IndexServletB;
import servlet.IndexServletC;
import servlet.IndexServletD;
import servlet.IndexServletE;
import servlet.IndexServletF;
import servlet.IndexServletG;
import workload.ComponentB;
import workload.ComponentC;
import workload.ComponentD;
import workload.ComponentE;
import workload.ComponentF;
import workload.ComponentG;
import workload.Proxy;
import workload.Workload;
import workload.ComponentA;

public class Startup implements javax.servlet.ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		String component = System.getenv("Component");
		if (component.equals("A")) {
			IndexServletA.workload = new ComponentA(System.getenv("IP_B") + "/SyntheticComponents/indexB");
		} else if (component.equals("B")) {
			IndexServletB.workload = new ComponentB(System.getenv("IP_C") + "/SyntheticComponents/indexC", System.getenv("IP_E") + "/SyntheticComponents/indexE");
		} else if (component.equals("C")) {
			IndexServletC.workload = new ComponentC(System.getenv("IP_D") + "/SyntheticComponents/indexD");
		} else if (component.equals("D")) {
			IndexServletD.workload = new ComponentD();
		} else if (component.equals("E")) {
			IndexServletE.workload = new ComponentE(System.getenv("IP_F") + "/SyntheticComponents/indexF", System.getenv("IP_G") + "/SyntheticComponents/indexG");
		} else if (component.equals("F")) {
			IndexServletF.workload = new ComponentF();
		} else if (component.equals("G")) {
			IndexServletG.workload = new ComponentG();
		} else if (component.equals("Proxy")) {
			IndexServletA.workload = new Proxy(System.getenv("IP_A") + "/SyntheticComponents/indexA");	//proxy also on indexA
		} else if (component.equals("FG")) {
			IndexServletF.workload = new ComponentF();
			IndexServletG.workload = new ComponentG();
		} else if (component.equals("BEFG")) {
			IndexServletF.workload = new ComponentF();
			IndexServletG.workload = new ComponentG();
			IndexServletE.workload = new ComponentE("localhost:8080/SyntheticComponents/indexF", "localhost:8080/SyntheticComponents/indexG");
			IndexServletB.workload = new ComponentB(System.getenv("IP_C") + "/SyntheticComponents/indexC", "localhost:8080/SyntheticComponents/indexE");
		} else if (component.equals("DG")) {
			IndexServletD.workload = new ComponentD();
			IndexServletG.workload = new ComponentG();
		} else if (component.equals("all")) {
			Workload.semaphore = new Semaphore(4, false);	//4 core support for coloc_all
			IndexServletF.workload = new ComponentF();
			IndexServletG.workload = new ComponentG();
			IndexServletE.workload = new ComponentE("localhost:8080/SyntheticComponents/indexF", "localhost:8080/SyntheticComponents/indexG");
			IndexServletD.workload = new ComponentD();
			IndexServletC.workload = new ComponentC("localhost:8080/SyntheticComponents/indexD");
			IndexServletB.workload = new ComponentB("localhost:8080/SyntheticComponents/indexC", "localhost:8080/SyntheticComponents/indexE");
			IndexServletA.workload = new ComponentA("localhost:8080/SyntheticComponents/indexB");
		}
	}
}
