package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentHiwiA extends Workload {

	public String ipC;
	public String ipB;

	public ComponentHiwiA(String ipB, String ipC) {
		this.ipC = ipC;
		this.ipB = ipB;
	}

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performExpWork(30);
		String result2 = callTo(ipB);
		String result3 = callTo(ipC);
		return "Served at Component A! --> " + result + "\n\t"+ result2.replace("\t", "\t\t") + "\n\t"+ result3.replace("\t", "\t\t");
	}

}
