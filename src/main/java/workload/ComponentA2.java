package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ComponentA2 extends Workload {
		
	public String ipB;
	private static Random rnd = new Random(123456789);
	
	public ComponentA2(String ipB) {
		this.ipB = ipB;
	}
	
	@Override
	public String performWork() throws UnsupportedEncodingException, IOException {
		double result = performConstantWork(30);
		String result2 = callTo(ipB);
		if(rnd.nextBoolean()) {
			result2 += callTo(ipB);
		}
		return "Served at Component A2! --> " + result + "\n\t"+ result2.replace("\t", "\t\t");
	}

}