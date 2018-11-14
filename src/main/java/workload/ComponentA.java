package workload;

public class ComponentA extends Workload {
	
	@Override
	public String performWork() {
		double lambda = 3;
		double result = performExpWork(lambda);
		return "Served at Component A! --> " + result;
	}

}