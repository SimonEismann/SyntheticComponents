package workload;

public class ComponentB extends Workload {
	
	@Override
	public String performWork() {
		double delay = 3;
		double result = performConstantWork(delay * 1000);
		return "Served at Component B! --> " + result;
	}

}