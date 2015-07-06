package org.nullbool.piexternal.game.api.wrappers.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IModel;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:13:07 <br>
 */
public class Model extends Renderable<IModel> implements IModel {

	public Model(IModel _model) {
		super(_model);
	}

	@Override
	public int getIndicesCount() {
		return _node.getIndicesCount();
	}

	@Override
	public int[] getIndicesX() {
		return getIndicesX();
	}

	@Override
	public int[] getIndicesY() {
		return getIndicesY();
	}

	@Override
	public int[] getIndicesZ() {
		return getIndicesZ();
	}

	@Override
	public int getTriangleCount() {
		return getTriangleCount();
	}

	@Override
	public int getVertexCount() {
		return getVertexCount();
	}

	@Override
	public int[] getVerticesX() {
		return getVerticesX();
	}

	@Override
	public int[] getVerticesY() {
		return getVerticesY();
	}

	@Override
	public int[] getVerticesZ() {
		return getVerticesZ();
	}
}