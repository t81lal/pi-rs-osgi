/**
 * 
 */
package org.nullbool.pi.core.scripting.api;

/**
 * @author Bibl (don't ban me pls)
 * @created 25 Jun 2015 00:13:50
 */
public abstract class Script {

	public abstract int run();
	
	public abstract void onStart();
	
	public abstract boolean canRun();

	public abstract void onStop();
	
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}