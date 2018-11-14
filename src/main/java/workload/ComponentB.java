package workload;

public class ComponentB extends Workload {
	
	@Override
	public String performWork() {
		double result = performConstantWork(3 * 1000);
		return "Served at Component B! --> " + result;
	}

}