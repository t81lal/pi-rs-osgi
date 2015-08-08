/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

package org.nullbool.pi.core.engine.impl.factory.revhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
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
