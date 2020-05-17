package workload;

public class ComponentHiwiC extends Workload {
	
	@Override
	public String performWork() {
		double result = performBranchWork(0.2, 100, 1000);
		return "Served at Component C! --> " + result;
	}

}