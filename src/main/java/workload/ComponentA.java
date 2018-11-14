package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentA extends Workload {
		
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double lambda = 3;
		double result = performExpWork(lambda);
		double result2 = callTo("http://10.1.3.48:2222");
		return "Served at Component A! --> " + result + " --> " + result2;
	}

}