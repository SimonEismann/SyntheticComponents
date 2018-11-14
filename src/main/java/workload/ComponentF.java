package workload;

public class ComponentF extends Workload {
	
	@Override
	public String performWork() {
		double result = performBranchWork(0.8, 5 * 1000, 1 * 1000);
		return "Served at Component F! --> " + result;
	}

}