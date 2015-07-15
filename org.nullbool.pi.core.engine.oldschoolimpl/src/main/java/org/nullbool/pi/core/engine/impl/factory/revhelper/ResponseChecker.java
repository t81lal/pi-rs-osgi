package org.nullbool.pi.core.engine.impl.factory.revhelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

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