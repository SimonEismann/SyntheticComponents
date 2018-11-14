package workload;

import java.util.Random;

public abstract class Workload {
	private Random rand = new Random();

	protected double performExpWork(double lambda) {
		double delay = Math.log(1-rand.nextDouble())/(-lambda) * 1000;
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

	public abstract String performWork();
}
