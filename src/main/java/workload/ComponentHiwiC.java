package workload;

public class ComponentHiwiC extends Workload {
	
	@Override
	public String performWork() {
		double result = performBranchWork(0.8, 5, 50);
		return "Served at Component C! --> " + result;
	}

}