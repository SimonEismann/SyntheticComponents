package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentB extends Workload {

	public String ipC;
	public String ipE;
	
	public ComponentB(String ipC, String ipE) {
		this.ipC = ipC;
		this.ipE = ipE;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performConstantWork(0.02 * 1000);
		String result2 = callTo(ipC);
		String result3 = callTo(ipE);
		return "Served at Component B! --> " + result + "\n\t"+ result2.replace("\t", "\t\t") + "\n\t"+ result3.replace("\t", "\t\t");
	}

}