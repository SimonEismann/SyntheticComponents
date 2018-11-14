package workload;

import java.text.DecimalFormat;
import java.util.Random;

public class ExpWorkload extends Workload {

	private double lambda;
	private Random rand = new Random();
	
	public ExpWorkload(int delay) {
		this.lambda = 1.0/(double)delay;
	}
	
	@Override
	public String performWork() {
		double delay = Math.log(1-rand.nextDouble())/(-lambda) * 1000;
		double result = performWork(delay);
		return "Exp workload with delay of " + new DecimalFormat("#0.00").format(delay/1000) + " seconds, result of " + result;
	}

}