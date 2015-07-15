package org.nullbool.pi.core.engine.impl.factory.revhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class RevHelper {

	public static String getServerAddress(int world) {
		return "oldschool" + world + ".runescape.com";
	}

	public static int getVersion(String address, int minor, int major) throws IOException {
		class DaemonFactory implements ThreadFactory {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		}

		ExecutorService service = Executors.newFixedThreadPool(4, new DaemonFactory());
		AtomicInteger i = new AtomicInteger();
		List<ResponseChecker> runs = new ArrayList<ResponseChecker>();
		while (true) {
			if (i.get() == 4) {
				for (ResponseChecker r : runs) {
					if (r.worked()) {
						service.shutdownNow();
						return r.version();
					}
				}
			} else {
				ListIterator<ResponseChecker> it = runs.listIterator();
				while (it.hasNext()) {
					ResponseChecker r = it.next();
					if (r.done()) {
						it.remove();
					}
				}
				for (int j = i.get(); j < 4; j++) {
					ResponseChecker run = new ResponseChecker(i, address, minor++);
					runs.add(run);
					service.execute(run);
				}
			}
		}
	}
}