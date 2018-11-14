package workload;

import java.util.Random;

public class ExpWorkload extends Workload {

	private int lambda;
	private Random rand = new Random();
	
	public ExpWorkload(int delay) {
		this.lambda = delay;
	}
	
	@Override
	public String performWork() {
		double delay = Math.log(1-rand.nextDouble())/(-lambda) * 1000;
		double result = performWork(delay);
		return "Exp workload with delay of " + delay + " seconds, result of " + result;
	}

}