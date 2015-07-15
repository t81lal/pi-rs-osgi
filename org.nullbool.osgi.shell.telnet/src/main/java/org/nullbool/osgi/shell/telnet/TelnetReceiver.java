package org.nullbool.osgi.shell.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.nullbool.osgi.shell.api.Shell;

public class TelnetReceiver implements Runnable {

	private final Shell shell;
	private final ServerSocket serverSocket;
	private final int maxConnections;
	private final List<TelnetHandlerThread> runningThreads;
	private Thread thisThread;

	public TelnetReceiver(Shell shell, ServerSocket socket, int maxConnections) {
		this.shell = shell;
		this.serverSocket = socket;
		this.maxConnections = maxConnections;
		this.runningThreads = new ArrayList<TelnetHandlerThread>();
		this.thisThread = new Thread(this);
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			final Socket socket;
			try {
				socket = serverSocket.accept();
			} catch (IOException ex) {
				if (Thread.interrupted()) {
					break;
				}
				throw new RuntimeException(ex);
			}

			synchronized (runningThreads) {
				if (runningThreads.size() >= maxConnections) {
					try {
						socket.getOutputStream().write("Too many connections at this time.".getBytes(/*"UTF-8"*/));
						socket.close();
					} catch (IOException e) {
					}
					continue;
				}
			}

			try {
				TelnetHandlerThread thread = new TelnetHandlerThread(shell, socket, socket.getInputStream(), socket.getOutputStream());
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException ex) {
				}

				continue;
			}
		}

		List<TelnetHandlerThread> threads;

		synchronized (runningThreads) {
			threads = new ArrayList<TelnetHandlerThread>(runningThreads);
		}

		for (TelnetHandlerThread thread : threads) {
			try {
				thread.stopAndWait();
			} catch (InterruptedException e) {
			}
		}
		
		runningThreads.clear();
		Thread.currentThread().interrupt();
	}

	public void start() {
		thisThread.start();
	}

	public void stop() throws InterruptedException {
		thisThread.interrupt();
		try {
			serverSocket.close();
		} catch (IOException e) {
		}
		thisThread.join();
	}

	private final class TelnetHandlerThread extends Thread {
		private final Shell shell;
		private final Socket socket;
		private final InputStream is;
		private final BufferedReader br;
		private final PrintStream outAndErr;

		public TelnetHandlerThread(Shell shell, Socket socket, InputStream in, OutputStream out) {
			this.shell = shell;
			this.socket = socket;
			this.is = in;
			this.br = new BufferedReader(new InputStreamReader(in));
			this.outAndErr = new PrintStream(out);
		}

		@Override
		public void start() {
			synchronized (runningThreads) {
				runningThreads.add(this);
			}
			super.start();
		}

		private void runImpl() {
			while (!Thread.currentThread().isInterrupted()) {
				outAndErr.print("-> ");

				String cmdLine;
				try {
					cmdLine = br.readLine();
				} catch (IOException ex) {
					if (!Thread.currentThread().isInterrupted()) {
						ex.printStackTrace(outAndErr);
						outAndErr.println("Unable to read from stdin - exiting now");
					}
					return;
				}

				if (cmdLine == null) {
					outAndErr.println("Bye bye");
					return;
				}

				try {
					shell.execute(cmdLine, is, outAndErr, outAndErr);
				} catch (Throwable t) {
					t.printStackTrace(outAndErr);
				}
			}
		}

		@Override
		public void run() {
			try {
				runImpl();
			} finally {
				synchronized (runningThreads) {
					runningThreads.remove(this);
				}
			}
		}

		public void stopAndWait() throws InterruptedException {
			if (!isAlive()) {
				return;
			}
			interrupt();
			try {
				socket.close();
			} catch (IOException e) {
			}
			join();
		}
	}
}