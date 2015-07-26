package org.nullbool.piexternal.game.api.wrappers.widget;

import org.nullbool.piexternal.game.api.accessors.widget.IWidgetNode;
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

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidgetNode#setWidgetId(int)
	 */
	@Override
	public void setWidgetId(int var1) {
		_node.setWidgetId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidgetNode#setType(int)
	 */
	@Override
	public void setType(int var1) {
		_node.setType(var1);		
	}
}