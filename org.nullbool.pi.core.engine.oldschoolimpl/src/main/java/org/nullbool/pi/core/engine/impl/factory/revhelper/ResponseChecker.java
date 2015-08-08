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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bibl (don't ban me pls)
 * @created some time before 8/8/15
 */
public class ResponseChecker implements Runnable {

	private final AtomicInteger i;
	private final String address;
	private final int version;
	private boolean worked = false;
	private boolean done = false;

	public ResponseChecker(AtomicInteger i, String address, int version) {
		this.i = i;
		this.address = address;
		this.version = version;
	}
	
	public int version() {
		return version;
	}
	
	public synchronized boolean worked() {
		return worked;
	}
	
	public synchronized boolean done() {
		return done;
	}

	@Override
	public void run() {
		try {
			i.incrementAndGet();
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(address, 43594), 8000);
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			output.write(getHandshake(version));
			output.flush();
			while (true) {
				if (input.available() <= 0)
					continue;
				int response = input.read();
				if (response == 0) {        // found version!
					worked = true;
				} else if (response == 6) { // bad version
					worked = false;
					socket.close();
					break;
				} else {
					socket.close();
					throw new IOException("Error.");
				}
			}
		} catch (Exception e) {
		} finally {
			i.decrementAndGet();
			done = true;
		}
	}
	
	private static byte[] getHandshake(int version) {
		ByteBuffer nigga = ByteBuffer.allocate(4 + 1); // handshake type + version
		nigga.put((byte) 15);
		nigga.putInt(version);
		return nigga.array();
	}
}
