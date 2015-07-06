package org.nullbool.piexternal.game.api.wrappers;

import org.nullbool.piexternal.game.api.accessors.IWrappedException;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 00:46:02
 */
public class WrappedException implements IWrappedException {

	private final IWrappedException obj;
	
	public WrappedException(IWrappedException obj) {
		this.obj = obj;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getThrowable()
	 */
	@Override
	public Throwable getThrowable() {
		return obj.getThrowable();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getReason_()
	 */
	@Override
	public String getReason_() {
		return obj.getReason_();
	}
}