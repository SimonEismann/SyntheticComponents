package workload;

public class ComponentG extends Workload {
	
	@Override
	public String performWork() {
		double result = performExpWork(0.03 * 1000);
		return "Served at Component G! --> " + result;
	}

}