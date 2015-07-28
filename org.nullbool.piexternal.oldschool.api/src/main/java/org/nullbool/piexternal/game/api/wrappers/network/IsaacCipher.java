/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.network;

import org.nullbool.piexternal.game.api.accessors.net.IIsaacCipher;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 23:23:27
 */
public class IsaacCipher implements IIsaacCipher {

	private final IIsaacCipher _obj;
	
	public IsaacCipher(IIsaacCipher _obj) {
		this._obj = _obj;
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#getCount()
	 */
	@Override
	public int getCount() {
		return _obj.getCount();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#getResults()
	 */
	@Override
	public int[] getResults() {
		return _obj.getResults();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#getMem()
	 */
	@Override
	public int[] getMem() {
		return _obj.getMem();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#init()
	 */
	@Override
	public void init() {
		_obj.init();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#next()
	 */
	@Override
	public int next() {
		return _obj.next();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#isaac()
	 */
	@Override
	public void isaac() {
		_obj.isaac();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#setCount(int)
	 */
	@Override
	public void setCount(int var1) {
		_obj.setCount(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#setResults(int[])
	 */
	@Override
	public void setResults(int[] var1) {
		_obj.setResults(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IIsaacCipher#setMem(int[])
	 */
	@Override
	public void setMem(int[] var1) {
		_obj.setMem(var1);		
	}
}