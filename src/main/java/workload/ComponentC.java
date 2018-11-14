package workload;

public class ComponentC extends Workload {
	
	@Override
	public String performWork() {
		double result = performNormalWork(2 * 1000, 1 * 1000);
		return "Served at Component C! --> " + result;
	}

}