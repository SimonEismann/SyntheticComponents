package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentB2 extends Workload {

	public String ipC;
	public String ipE;
	
	public ComponentB2(String ipC, String ipE) {
		this.ipC = ipC;
		this.ipE = ipE;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performExpWork(15);
		String result2 = callTo(ipC);
		String result3 = callTo(ipE);
		return "Served at Component B2! --> " + result + "\n\t"+ result2.replace("\t", "\t\t") + "\n\t"+ result3.replace("\t", "\t\t");
	}

}