package workload;

public class ConstantWorkload extends Workload {

	private int delay;
	
	public ConstantWorkload(int delay) {
		this.delay = delay;
	}
	
	@Override
	public String performWork() {
		double result = performWork(delay * 1000);
		return "Constant workload with delay of " + delay + " seconds, result of " + result;
	}

}
