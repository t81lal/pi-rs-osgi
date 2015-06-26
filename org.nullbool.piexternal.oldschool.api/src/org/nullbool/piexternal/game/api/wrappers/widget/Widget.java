package org.nullbool.piexternal.game.api.wrappers.widget;

import java.util.ArrayList;
import java.util.List;

import org.nullbool.piexternal.game.api.accessors.widgets.IWidget;
import org.nullbool.piexternal.game.api.wrappers.collection.Node;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 20 Apr 2015 at 21:16:15 <br>
 */
public class Widget extends Node<IWidget> implements IWidget {

	public Widget(IWidget _widget) {
		super(_widget);
	}

	@Override
	public String[] getActions() {
		return _node.getActions();
	}

	@Override
	public int getBorderThickness() {
		return _node.getBorderThickness();
	}

	@Override
	public int getBoundsIndex() {
		return _node.getBoundsIndex();
	}

	@Override
	public IWidget[] getChildren() {
		IWidget[] _children = _node.getChildren();
		if (_children == null)
			return null;
		final int length = _children.length;
		List<Widget> children = new ArrayList<Widget>(length);
		for (int i = 0; i < length; i++) {
			IWidget child = _children[i];
			if (child != null)
				children.add(new Widget(child));
		}
		return children.toArray(new Widget[children.size()]);
	}

	@Override
	public int getHeight() {
		return _node.getHeight();
	}

	@Override
	public int getIndex() {
		return _node.getIndex();
	}

	@Override
	public int getItemId() {
		return _node.getItemId();
	}

	@Override
	public int[] getItemIds() {
		return _node.getItemIds();
	}

	@Override
	public int getModelType() {
		return _node.getModelType();
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public int getParentId() {
		return _node.getParentId();
	}

	@Override
	public int[] getQuantities() {
		return _node.getQuantities();
	}

	@Override
	public int getRelativeX() {
		return _node.getRelativeX();
	}

	@Override
	public int getRelativeY() {
		return _node.getRelativeY();
	}

	@Override
	public int getRotationX() {
		return _node.getRotationX();
	}

	@Override
	public int getRotationY() {
		return _node.getRotationY();
	}

	@Override
	public int getRotationZ() {
		return _node.getRotationZ();
	}

	@Override
	public int getScrollX() {
		return _node.getScrollX();
	}

	@Override
	public int getScrollY() {
		return _node.getScrollY();
	}

	@Override
	public int getStackSize() {
		return _node.getStackSize();
	}

	@Override
	public String getText() {
		return _node.getText();
	}

	@Override
	public int getTextAlpha() {
		return _node.getTextAlpha();
	}

	@Override
	public int getTextColor() {
		return _node.getTextColor();
	}

	@Override
	public int getTextureId() {
		return _node.getTextureId();
	}

	@Override
	public int getUID() {
		return _node.getUID();
	}

	@Override
	public int getWidgetType() {
		return _node.getWidgetType();
	}

	@Override
	public int getWidth() {
		return _node.getWidth();
	}
}