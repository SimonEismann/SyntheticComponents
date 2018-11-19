package workload;

public class ComponentF extends Workload {
	
	@Override
	public String performWork() {
		double result = performBranchWork(0.8, 0.01 * 1000, 0.05 * 1000);
		return "Served at Component F! --> " + result;
	}

}