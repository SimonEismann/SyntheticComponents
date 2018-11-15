package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentE extends Workload {

	public String ipF;
	public String ipG;
	
	public ComponentE(String ipF, String ipG) {
		this.ipF = ipF;
		this.ipG = ipG;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performExpWork(0.3 * 1000);
		String result2 = callTo(ipF);
		String result3 = callTo(ipG);
		return "Served at Component E! --> " + result + "\n\t"+ result2.replace("\t", "\t\t")+ "\n\t"+ result3.replace("\t", "\t\t");
	}

}