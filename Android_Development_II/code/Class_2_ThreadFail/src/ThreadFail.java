
public class ThreadFail {
	
	private int testVal = 0;
	
	public synchronized void increment() {
		testVal++;
	}
	
	public synchronized void decrement() {
		testVal--;
	}
	
	public int getTestVal() {
		return testVal;
	}
	
	public static void main(String[] args) {
		final ThreadFail tf = new ThreadFail();
		
		Thread incThread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					tf.increment();
				}
			}
		};
		incThread.start();
		
		Thread decThread = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					tf.decrement();
				}
			}
		};
		decThread.start();
		
		try {
			incThread.join();
			decThread.join();
		} catch (InterruptedException ie) {
			System.out.println("Interrupted");
		}
		
		System.out.println(tf.getTestVal());
	}
}
