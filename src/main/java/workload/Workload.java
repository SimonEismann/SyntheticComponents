package workload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Random;

import servlet.LoggingExtCall1;
import servlet.LoggingExtCall2;

public abstract class Workload {
	private Random rand = new Random();

	protected String callTo(String ipAndPort, boolean call1) throws UnsupportedEncodingException, IOException {
		long tic = System.currentTimeMillis();
		URL url = new URL("http://" + ipAndPort + "/SyntheticComponents/index");
		String result = "";
		boolean first = true;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
			for (String line; (line = reader.readLine()) != null;) {
				if (first)
					first = false;
				else
					result += "\n";
				result += line;
			}
		}
		long toc = System.currentTimeMillis();
		if (call1)
			LoggingExtCall1.globalQueue.add(tic + "," + toc); 
		else
			LoggingExtCall2.globalQueue.add(tic + "," + toc); 
		return result;
	}

	protected double performNormalWork(double mean, double std) {
		double result = performConstantWork(rand.nextGaussian() * std + mean);
		return result;
	}

	protected double performExpWork(double lambda) {
		double delay = Math.log(1 - rand.nextDouble()) / (-lambda) * 1000;
		double result = performConstantWork(delay);
		return result;
	}

	protected double performBranchWork(double chance, double delayA, double delayB) {
		if (chance > rand.nextDouble()) {
			return performConstantWork(delayA);
		}
		return performConstantWork(delayB);
	}

	protected double performConstantWork(double milliseconds) {
		boolean plus = true;
		double sum = 0;
		double i = 1.0;
		long start = System.currentTimeMillis();
		while (true) {
			if (plus) {
				sum += 1.0 / i;
				plus = false;
			} else {
				sum -= 1.0 / i;
				plus = true;
			}
			i += 2.0;
			if (System.currentTimeMillis() - start > milliseconds)
				break;
		}
		sum *= 4;
		return sum;
	}

	public abstract String performWork() throws UnsupportedEncodingException, IOException, InterruptedException;
}
