package org.nullbool.piexternal.game.api.wrappers.widget;

import org.nullbool.piexternal.game.api.accessors.widgets.IWidgetNode;
import org.nullbool.piexternal.game.api.wrappers.collection.Node;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:15:38 <br>
 */
public class WidgetNode extends Node<IWidgetNode> implements IWidgetNode {

	public WidgetNode(IWidgetNode _widgetNode) {
		super(_widgetNode);
	}

	@Override
	public int getType() {
		return _node.getType();
	}

	@Override
	public int getWidgetId() {
		return _node.getWidgetId();
	}
}