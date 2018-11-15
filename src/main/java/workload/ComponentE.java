package workload;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ComponentE extends Workload {
	private int limit = 2;
	private int count = 0;
	private Object lock = new Object();

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
				ok = count < limit - 1;
			}
			while (!ok)
				if (ok) {
					count++;
					double result = performExpWork(30 * 1000);
					String result2 = callTo(ipF);
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