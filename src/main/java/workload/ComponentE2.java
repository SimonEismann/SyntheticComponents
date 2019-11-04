package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentE2 extends Workload {

	public String ipF;
	public String ipG;

	public ComponentE2(String ipF, String ipG) {
		this.ipF = ipF;
		this.ipG = ipG;
	}

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException, InterruptedException {
		double result = performExpWork(0.005 * 1000);
		String result2 = callTo(ipF);
		String result3 = callTo(ipG);
		result3 += callTo(ipG);
		return "Served at Component E2! --> " + result + "\n\t" + result2.replace("\t", "\t\t") + "\n\t"
				+ result3.replace("\t", "\t\t");
	}

}