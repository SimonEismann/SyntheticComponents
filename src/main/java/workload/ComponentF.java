package workload;

public class ComponentF extends Workload {
	
	@Override
	public String performWork() {
		double result = performBranchWork(0.8, 0.02 * 1000, 0.10 * 1000);
		return "Served at Component F! --> " + result;
	}

}