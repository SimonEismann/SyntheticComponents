package workload;

public class ComponentHiwiB extends Workload {
	
	@Override
	public String performWork() {
		double result = performExpWork(300);
		return "Served at Component B! --> " + result;
	}

}