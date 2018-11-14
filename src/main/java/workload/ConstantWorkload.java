package workload;

public class ConstantWorkload implements Workload {

	private int delay;
	
	public ConstantWorkload(int delay) {
		this.delay = delay;
	}
	
	@Override
	public String performWork() {
		return "Constant workload with delay of " + delay + " seconds";
	}

}
