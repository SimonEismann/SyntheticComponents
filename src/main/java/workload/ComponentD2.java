package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentD2 extends Workload {
			
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performExpWork(10);
		return "Served at Component D! --> " + result;
	}

} 