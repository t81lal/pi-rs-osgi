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

	@Deprecated
	public int getScrollX() {
		return _node.getInsetX();
	}

	@Deprecated
	public int getScrollY() {
		return _node.getInsetY();
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

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getModelId()
	 */
	@Override
	public int getModelId() {
		return _node.getModelId();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#isHidden()
	 */
	@Override
	public boolean isHidden() {
		return _node.isHidden();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getInsetX()
	 */
	@Override
	public int getInsetX() {
		return _node.getInsetX();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getInsetY()
	 */
	@Override
	public int getInsetY() {
		return _node.getInsetY();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getViewportWidth()
	 */
	@Override
	public int getViewportWidth() {
		return _node.getViewportWidth();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getViewportHeight()
	 */
	@Override
	public int getViewportHeight() {
		return _node.getViewportHeight();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getModelZoom()
	 */
	@Override
	public int getModelZoom() {
		return _node.getModelZoom();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getParent()
	 */
	@Override
	public IWidget getParent() {
		IWidget _parent = _node.getParent();
		if(_parent == null) {
			return null;
		}
		return new Widget(_parent);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getSelectedAction()
	 */
	@Override
	public String getSelectedAction() {
		return _node.getSelectedAction();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getButtonType()
	 */
	@Override
	public int getButtonType() {
		return _node.getButtonType();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getMouseEnterListener()
	 */
	@Override
	public Object[] getMouseEnterListener() {
		return _node.getMouseEnterListener();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getMouseExitListener()
	 */
	@Override
	public Object[] getMouseExitListener() {
		return _node.getMouseExitListener();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getMouseHoverListener()
	 */
	@Override
	public Object[] getMouseHoverListener() {
		return _node.getMouseHoverListener();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getConfigListenerArgs()
	 */
	@Override
	public Object[] getConfigListenerArgs() {
		return _node.getConfigListenerArgs();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getConfigTriggers()
	 */
	@Override
	public int[] getConfigTriggers() {
		return _node.getConfigTriggers();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getRenderListener()
	 */
	@Override
	public Object[] getRenderListener() {
		return _node.getRenderListener();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getTableListenerArgs()
	 */
	@Override
	public Object[] getTableListenerArgs() {
		return _node.getTableListenerArgs();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getTableModTriggers()
	 */
	@Override
	public int[] getTableModTriggers() {
		return _node.getTableModTriggers();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getSkillListenerArgs()
	 */
	@Override
	public Object[] getSkillListenerArgs() {
		return _node.getSkillListenerArgs();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getSkillTriggers()
	 */
	@Override
	public int[] getSkillTriggers() {
		return _node.getSkillTriggers();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getScrollListeners()
	 */
	@Override
	public Object[] getScrollListeners() {
		return _node.getScrollListeners();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getFontId()
	 */
	@Override
	public int getFontId() {
		return _node.getFontId();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#isTextShadowed()
	 */
	@Override
	public boolean isTextShadowed() {
		return _node.isTextShadowed();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getShadowColour()
	 */
	@Override
	public int getShadowColour() {
		return _node.getShadowColour();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#isFlippedVertically()
	 */
	@Override
	public boolean isFlippedVertically() {
		return _node.isFlippedVertically();
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#isFlippedHorizontally()
	 */
	@Override
	public boolean isFlippedHorizontally() {
		return _node.isFlippedHorizontally();
	}
}