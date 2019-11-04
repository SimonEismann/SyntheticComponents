package workload;

public class ComponentF2 extends Workload {
	
	@Override
	public String performWork() {
		double result = performNormalWork(40, 2);
		return "Served at Component F2! --> " + result;
	}

}