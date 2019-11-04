package startup;

import java.util.concurrent.Semaphore;

import javax.servlet.ServletContextEvent;

import servlet.*;
import workload.*;

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
			IndexServletA2.workload = new Proxy(System.getenv("IP_A") + "/SyntheticComponents/indexA2");
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
		} else if (component.equals("CD")) {
			IndexServletD.workload = new ComponentD();
			IndexServletC.workload = new ComponentC("localhost:8080/SyntheticComponents/indexD");
		} else if (component.equals("all")) {
			Workload.semaphore = new Semaphore(8, false);	//4 core support for coloc_all
			IndexServletF.workload = new ComponentF();
			IndexServletG.workload = new ComponentG();
			IndexServletE.workload = new ComponentE("localhost:8080/SyntheticComponents/indexF", "localhost:8080/SyntheticComponents/indexG");
			IndexServletD.workload = new ComponentD();
			IndexServletC.workload = new ComponentC("localhost:8080/SyntheticComponents/indexD");
			IndexServletB.workload = new ComponentB("localhost:8080/SyntheticComponents/indexC", "localhost:8080/SyntheticComponents/indexE");
			IndexServletA.workload = new ComponentA("localhost:8080/SyntheticComponents/indexB");
		} else if (component.equals("mwcA")) {
			IndexServletA.workload = new ComponentA(System.getenv("IP_B") + "/SyntheticComponents/indexB");
			IndexServletA2.workload = new ComponentA2(System.getenv("IP_B") + "/SyntheticComponents/indexB2");
		} else if (component.equals("mwcB")) {
			IndexServletB.workload = new ComponentB(System.getenv("IP_C") + "/SyntheticComponents/indexC", System.getenv("IP_E") + "/SyntheticComponents/indexE");
			IndexServletB2.workload = new ComponentB2(System.getenv("IP_C") + "/SyntheticComponents/indexC2", System.getenv("IP_E") + "/SyntheticComponents/indexE2");
		} else if (component.equals("mwcC")) {
			IndexServletC.workload = new ComponentC(System.getenv("IP_D") + "/SyntheticComponents/indexD");
			IndexServletC2.workload = new ComponentC2(System.getenv("IP_D") + "/SyntheticComponents/indexD");
		} else if (component.equals("mwcD")) {
			IndexServletD.workload = new ComponentD();
			IndexServletD2.workload = new ComponentD2();
		} else if (component.equals("mwcE")) {
			IndexServletE.workload = new ComponentE(System.getenv("IP_F") + "/SyntheticComponents/indexF", System.getenv("IP_G") + "/SyntheticComponents/indexG");
			IndexServletE2.workload = new ComponentE2(System.getenv("IP_F") + "/SyntheticComponents/indexF2", System.getenv("IP_G") + "/SyntheticComponents/indexG");
		} else if (component.equals("mwcFG")) {
			IndexServletF.workload = new ComponentF();
			IndexServletG.workload = new ComponentG();
			IndexServletF2.workload = new ComponentF2();
		}
	}
}
