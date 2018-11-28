package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Proxy extends Workload {
		
	public String ipA;
	
	public Proxy(String ipA) {
		this.ipA = ipA;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		String result2 = callTo(ipA, true);
		return "Served at Proxy! \n\t"+ result2.replace("\t", "\t\t");
	}

}