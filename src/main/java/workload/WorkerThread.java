package workload;

public class WorkerThread implements Runnable {

	boolean interupted = false;
	
	@Override
	public void run() {
		int number = 0;
		while (!interupted)
			number += Math.random();
		System.out.println(number);
	}

}
