package workload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Semaphore;

import servlet.LoggingExtCall1;
import servlet.LoggingExtCall2;
import servlet.LoggingInternal;

public abstract class Workload {
	private Random rand = new Random();
	private static Semaphore sem = new Semaphore(2);

	protected String callTo(String ipAndPort, boolean call1) throws UnsupportedEncodingException, IOException {
		long tic = System.nanoTime();
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
		long toc = System.nanoTime();
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

	protected double performExpWork(double mean) {
		double lambda = 1 / mean;
		double delay = Math.log(1 - rand.nextDouble()) / (-lambda);
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
		long tic = System.nanoTime();
		WorkerThread worker = new WorkerThread();
		Thread thread = new Thread(worker);
		thread.start();
		long tac = System.nanoTime();
		long sleep = ((long) milliseconds - (tac - tic) / 1000000);
		if (sleep > 0) {
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		worker.interupted = true;
		long toc = System.nanoTime();
		LoggingInternal.globalQueue.add((toc - tic) + "," + tic + "," + toc + "," + tac);
		return 1;
	}

	public abstract String performWork() throws UnsupportedEncodingException, IOException, InterruptedException;
}
