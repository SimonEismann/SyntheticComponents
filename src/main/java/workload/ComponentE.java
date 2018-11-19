package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

	@Override
	public String performWork() throws UnsupportedEncodingException, IOException, InterruptedException {
		while (true) {
			boolean ok;
			synchronized (lock) {
				ok = count < limit;
			}
			if (ok) {
				synchronized (lock) {
					count++;
				}
				double result = performExpWork(0.005 * 1000);
				String result2 = callTo(ipF, true);
				String result3 = callTo(ipG, false);
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