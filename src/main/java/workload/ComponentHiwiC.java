package workload;

public class ComponentHiwiC extends Workload {

	@Override
	public String performWork() {
		double result = performExpWork(25);
		return "Served at Component C! --> " + result;
	}

}
