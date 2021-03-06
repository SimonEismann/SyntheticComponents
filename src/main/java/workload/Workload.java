package workload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Semaphore;

import servlet.LoggingExtCallA;
import servlet.LoggingExtCallB;
import servlet.LoggingExtCallC;
import servlet.LoggingExtCallD;
import servlet.LoggingExtCallE;
import servlet.LoggingExtCallF;
import servlet.LoggingExtCallG;
import servlet.LoggingInternalA;
import servlet.LoggingInternalB;
import servlet.LoggingInternalC;
import servlet.LoggingInternalD;
import servlet.LoggingInternalE;
import servlet.LoggingInternalF;
import servlet.LoggingInternalG;

public abstract class Workload {
	public static Semaphore semaphore = new Semaphore(2, false);
	
	private Random rand = new Random();

	protected String callTo(String ipAndPort) throws UnsupportedEncodingException, IOException {
		long tic = System.nanoTime();
		URL url = new URL("http://" + ipAndPort);
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
		switch (ipAndPort.charAt(ipAndPort.length() - 1)){
			case 'A':
				LoggingExtCallA.globalQueue.add(tic + "," + toc + ",1");
				break;
			case 'B':
				LoggingExtCallB.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case 'C':
				LoggingExtCallC.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case 'D':
				LoggingExtCallD.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case 'E':
				LoggingExtCallE.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case 'F':
				LoggingExtCallF.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case 'G':
				LoggingExtCallG.globalQueue.add(tic + "," + toc+ ",1");
				break;
			case '2':
				switch (ipAndPort.charAt(ipAndPort.length() - 2)){
					case 'A':
						LoggingExtCallA.globalQueue.add(tic + "," + toc+ ",2");
						break;
					case 'B':
						LoggingExtCallB.globalQueue.add(tic + "," + toc+ ",2");
						break;
					case 'C':
						LoggingExtCallC.globalQueue.add(tic + "," + toc+ ",2");
						break;
					case 'D':
						LoggingExtCallD.globalQueue.add(tic + "," + toc+ ",2");
						break;
					case 'E':
						LoggingExtCallE.globalQueue.add(tic + "," + toc+ ",2");
						break;
					case 'F':
						LoggingExtCallF.globalQueue.add(tic + "," + toc+ ",2");
						break;
					default:
						throw new IllegalStateException("Component type doesn't match");
				}
				break;
			default:
				throw new IllegalStateException("Component type doesn't match");
		}
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
		try {
			semaphore.acquire();
			long start = System.nanoTime();
			int i = 0;
			
			while (true) {
				if (System.nanoTime() - start > milliseconds * 1000000)
					break;
			}
			
			long end = System.nanoTime();
			switch (this.getClass().getName().charAt(this.getClass().getName().length() - 1)){
			case 'A':
				LoggingInternalA.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'B':
				LoggingInternalB.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'C':
				LoggingInternalC.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'D':
				LoggingInternalD.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'E':
				LoggingInternalE.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'F':
				LoggingInternalF.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'G':
				LoggingInternalG.globalQueue
				.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
						+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
				break;
			case 'y':
				//Proxy class
				break;
			case '2':
				switch (this.getClass().getName().charAt(this.getClass().getName().length() - 2)){
				case 'A':
					LoggingInternalA.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				case 'B':
					LoggingInternalB.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				case 'C':
					LoggingInternalC.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				case 'D':
					LoggingInternalD.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				case 'E':
					LoggingInternalE.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				case 'F':
					LoggingInternalF.globalQueue
					.add(i + "," + Math.floor(milliseconds * 1000000) + "," + (end - start) + ","
							+ (Math.floor(milliseconds * 1000000) - end + start) + "," + start + "," + end);
					break;
				default:
					throw new IllegalStateException("Workload class: No internal logger for class");
				}
				break;
			default:
				throw new IllegalStateException("Workload class: No internal logger for class");
			}
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
		return 1;
	}

	public abstract String performWork() throws UnsupportedEncodingException, IOException, InterruptedException;
}
