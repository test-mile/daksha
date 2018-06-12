package daksha.tpi.sysauto.utils;

import daksha.core.batteries.config.Batteries;

public class ThreadUtils {

	public synchronized static String getCurrentThreadName() {
		return Thread.currentThread().getName();
	}

	public synchronized static Thread createThread(String threadName, Runnable runnable) throws Exception {
		Batteries.registerThread(ThreadUtils.getCurrentThreadName(), threadName);
		return new Thread(runnable, threadName);
	}

	public synchronized static Thread createBaseThread(String threadName, Runnable runnable) throws Exception {
		Batteries.registerNewThread(threadName);
		return new Thread(runnable, threadName);
	}
}
