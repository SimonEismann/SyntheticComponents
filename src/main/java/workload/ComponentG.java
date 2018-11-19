package workload;

public class ComponentG extends Workload {
	
	@Override
	public String performWork() {
		double result = performExpWork(0.04 * 1000);
		return "Served at Component G! --> " + result;
	}

}