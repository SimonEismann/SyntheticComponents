package workload;

public class ComponentHiwiB extends Workload {
	
	@Override
	public String performWork() {
		double result = performExpWork(15);
		return "Served at Component B! --> " + result;
	}

}