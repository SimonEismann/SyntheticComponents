package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import servlet.LoggingExtCall1;

public class ComponentE extends Workload {
	private static int limit = 2;
	private static int count = 0;
	private static Object lock = new Object();

	public String ipF;
	public String ipG;

	public ComponentE(String ipF, String ipG) {
		this.ipF = ipF;
		this.ipG = ipG;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, IOException, InterruptedException {
		ComponentE e = new ComponentE("10.1.3.48:6666", "10.1.3.48:7777");
		e.performWork();
	}

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException, InterruptedException {
		while (true) {
			boolean ok;
			synchronized (lock) {
				ok = count < limit;
			}
			if (ok) {
				count++;
				double result = performExpWork(0.3 * 1000);
				long tic = System.currentTimeMillis();
				String result2 = callTo(ipF);
				long toc = System.currentTimeMillis();
				LoggingExtCall1.globalQueue.add(tic + "," + toc); 
				String result3 = callTo(ipG);
				synchronized (lock) {
					count--;
				}
				return "Served at Component E! --> " + result + "\n\t" + result2.replace("\t", "\t\t") + "\n\t"
						+ result3.replace("\t", "\t\t");
			} else {
				Thread.sleep(100);
				return performWork();
			}
		}
	}

}