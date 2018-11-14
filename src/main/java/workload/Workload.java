package workload;

public abstract class Workload {

	protected double performWork(double milliseconds) {
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
