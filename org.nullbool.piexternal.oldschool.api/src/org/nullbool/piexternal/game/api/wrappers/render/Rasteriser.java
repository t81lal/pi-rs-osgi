/**
 * 
 */
package org.nullbool.piexternal.game.api.wrappers.render;

import org.nullbool.piexternal.game.api.accessors.render.IRasteriser;
import org.nullbool.piexternal.game.api.wrappers.collection.DualNode;

/**
 * @author Bibl (don't ban me pls)
 * @created 22 Jun 2015 23:28:01
 */
public class Rasteriser extends DualNode<IRasteriser> implements IRasteriser {

	/**
	 * @param _node
	 */
	public Rasteriser(IRasteriser _node) {
		super(_node);
	}
}