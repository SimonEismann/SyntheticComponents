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
		double result = performConstantWork(0.03 * 1000);
		String result2 = callTo(ipC, true);
		String result3 = callTo(ipE, false);
		return "Served at Component B! --> " + result + "\n\t"+ result2.replace("\t", "\t\t") + "\n\t"+ result3.replace("\t", "\t\t");
	}

}