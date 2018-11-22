package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentD extends Workload {
			
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performNormalWork(0.01 * 1000, 0.001 * 1000);
		return "Served at Component D! --> " + result;
	}

} 