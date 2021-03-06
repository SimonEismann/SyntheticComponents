package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentC2 extends Workload {

	public String ipD;

	public ComponentC2(String ipD) {
		this.ipD = ipD;
	}

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performNormalWork(0.02 * 1000, 0.001 * 1000);
		String resultS = "Served at Component C2! --> " + result;
		resultS += callTo(ipD);
		resultS += callTo(ipD + "2");
		return resultS;
	}

}