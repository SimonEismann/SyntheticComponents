package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentA extends Workload {
		
	public String ipB;
	
	public ComponentA(String ipB) {
		this.ipB = ipB;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performExpWork(0.3);
		String result2 = callTo(ipB, true);
		return "Served at Component A! --> " + result + "\n\t"+ result2.replace("\t", "\t\t");
	}

}