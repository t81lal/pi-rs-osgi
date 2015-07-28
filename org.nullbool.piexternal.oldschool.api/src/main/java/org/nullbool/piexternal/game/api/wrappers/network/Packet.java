/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.network;

import org.nullbool.piexternal.game.api.accessors.net.IIsaacCipher;
import org.nullbool.piexternal.game.api.accessors.net.IPacket;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 23:18:21
 */
public class Packet extends Buffer<IPacket> implements IPacket {

	/**
	 * @param _node
	 */
	public Packet(IPacket _node) {
		super(_node);
	}
	
	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#getCipher()
	 */
	@Override
	public IsaacCipher getCipher() {
		IIsaacCipher _cipher = _node.getCipher();
		if(_cipher == null)
			return null;
		return new IsaacCipher(_cipher);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#getBitCaret()
	 */
	@Override
	public int getBitCaret() {
		return _node.getBitCaret();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#initCipher(int[])
	 */
	@Override
	public void initCipher(int[] var1) {
		_node.initCipher(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#initBitAccess()
	 */
	@Override
	public void initBitAccess() {
		_node.initBitAccess();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#readBits(int)
	 */
	@Override
	public int readBits(int var1) {
		return _node.readBits(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#readableBytes(int)
	 */
	@Override
	public int readableBytes(int var1) {
		return _node.readableBytes(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#finishBitAccess()
	 */
	@Override
	public void finishBitAccess() {
		_node.finishBitAccess();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IPacket#setBitCaret(int)
	 */
	@Override
	public void setBitCaret(int var1) {
		_node.setBitCaret(var1);		
	}

	@Override
	public int readPacketHeader() {
		return _node.readPacketHeader();
	}

	@Override
	public void writePacketHeader(int var1) {
		_node.writePacketHeader(var1);
	}
}