package org.nullbool.piexternal.game.api.wrappers;

import org.nullbool.piexternal.game.api.accessors.IWrappedException;

/**
 * @author Bibl (don't ban me pls)
 * @created 26 Jun 2015 00:46:02
 */
public class WrappedException implements IWrappedException {

	private final IWrappedException _obj;
	
	public WrappedException(IWrappedException obj) {
		this._obj = obj;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getThrowable()
	 */
	@Override
	public Throwable getThrowable() {
		return _obj.getThrowable();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#getReason_()
	 */
	@Override
	public String getReason_() {
		return _obj.getReason_();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#setReason_(java.lang.String)
	 */
	@Override
	public void setReason_(String var1) {
		_obj.setReason_(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.IWrappedException#setThrowable(java.lang.Throwable)
	 */
	@Override
	public void setThrowable(Throwable var1) {
		_obj.setThrowable(var1);		
	}
}