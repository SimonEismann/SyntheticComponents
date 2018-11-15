package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentB extends Workload {

	public String ipC;
	
	public ComponentB(String ipC) {
		this.ipC = ipC;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performConstantWork(3 * 1000);
		String result2 = callTo(ipC);
		return "Served at Component B! --> " + result + "\n\t"+ result2;
	}

}