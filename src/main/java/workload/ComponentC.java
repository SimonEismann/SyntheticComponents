package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentC extends Workload {

	public String ipD;

	public ComponentC(String ipD) {
		this.ipD = ipD;
	}

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performNormalWork(0.02 * 1000, 0.01 * 1000);
		String resultS = "Served at Component C! --> " + result;
		for (int i = 0; i < 5; i++) {
			String result2 = callTo(ipD, true);
			resultS += "\n\t" + result2.replace("\t", "\t\t");
		}
		return resultS;
	}

}