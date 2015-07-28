/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.network;

import java.math.BigInteger;

import org.nullbool.piexternal.game.api.accessors.net.IBuffer;
import org.nullbool.piexternal.game.api.wrappers.collection.Node;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 22:56:54
 */
public class Buffer<T extends IBuffer> extends Node<T> implements IBuffer {

	/**
	 * @param _node
	 */
	public Buffer(T _node) {
		super(_node);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#getCaret()
	 */
	@Override
	public int getCaret() {
		return _node.getCaret();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#getPayload()
	 */
	@Override
	public byte[] getPayload() {
		return _node.getPayload();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write8(int)
	 */
	@Override
	public void write8(int var1) {
		_node.write8(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write24(int)
	 */
	@Override
	public void write24(int var1) {
		_node.write24(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write32(int)
	 */
	@Override
	public void write32(int var1) {
		_node.write32(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write40(long)
	 */
	@Override
	public void write40(long var1) {
		_node.write40(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeCharSequence(java.lang.CharSequence)
	 */
	@Override
	public void writeCharSequence(CharSequence var1) {
		_node.writeCharSequence(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeBytes(byte[], int, int)
	 */
	@Override
	public void writeBytes(byte[] var1, int var2, int var3) {
		_node.writeBytes(var1, var2, var3);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write32Weird(int)
	 */
	@Override
	public void write32Weird(int var1) {
		_node.write32Weird(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeLE16A(int)
	 */
	@Override
	public void writeLE16A(int var1) {
		_node.writeLE16A(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeVarByte(int)
	 */
	@Override
	public void writeVarByte(int var1) {
		_node.writeVarByte(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write8Weird(int)
	 */
	@Override
	public void write8Weird(int var1) {
		_node.write8Weird(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#enableEncryption(java.math.BigInteger, java.math.BigInteger)
	 */
	@Override
	public void enableEncryption(BigInteger var1, BigInteger var2) {
		_node.enableEncryption(var1, var2);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write8Offset128(int)
	 */
	@Override
	public void write8Offset128(int var1) {
		_node.write8Offset128(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write8Neg0(int)
	 */
	@Override
	public void write8Neg0(int var1) {
		_node.write8Neg0(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write16A(int)
	 */
	@Override
	public void write16A(int var1) {
		_node.write16A(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write16B(int)
	 */
	@Override
	public void write16B(int var1) {
		_node.write16B(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeLE32(int)
	 */
	@Override
	public void writeLE32(int var1) {
		_node.writeLE32(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeInvertedLE32(int)
	 */
	@Override
	public void writeInvertedLE32(int var1) {
		_node.writeInvertedLE32(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeInverted32(int)
	 */
	@Override
	public void writeInverted32(int var1) {
		_node.writeInverted32(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write64(long)
	 */
	@Override
	public void write64(long var1) {
		_node.write64(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write16(int)
	 */
	@Override
	public void write16(int var1) {
		_node.write16(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeString(java.lang.String)
	 */
	@Override
	public void writeString(String var1) {
		_node.writeString(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeLE16(int)
	 */
	@Override
	public void writeLE16(int var1) {
		_node.writeLE16(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#writeJagexString(java.lang.String)
	 */
	@Override
	public void writeJagexString(String var1) {
		_node.writeJagexString(var1);
	}

//	/* (non-Javadoc)
//	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#readBytesB(byte[], int, int)
//	 */
//	@Override
//	public void readBytesB(byte[] var1, int var2, int var3) {
//		_node.readBytesB(var1, var2, var3);
//	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#write8Neg128(int)
	 */
	@Override
	public void write8Neg128(int var1) {
		_node.write8Neg128(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#setPayload(byte[])
	 */
	@Override
	public void setPayload(byte[] var1) {
		_node.setPayload(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#setCaret(int)
	 */
	@Override
	public void setCaret(int var1) {
		_node.setCaret(var1);		
	}

//	/* (non-Javadoc)
//	 * @see org.nullbool.piexternal.game.api.accessors.network.IBuffer#readBytesA(byte[], int, int)
//	 */
//	@Override
//	public void readBytesA(byte[] var1, int var2, int var3) {
//		_node.readBytesA(var1, var2, var3);
//	}
}