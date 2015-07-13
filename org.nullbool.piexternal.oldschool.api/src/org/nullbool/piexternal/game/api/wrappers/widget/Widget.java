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
	public int getUid() {
		return _node.getUid();
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

//	/* (non-Javadoc)
//	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#getButtonType()
//	 */
//	@Override
//	public int getButtonType() {
//		return _node.getButtonType();
//	}

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

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setQuantities(int[])
	 */
	@Override
	public void setQuantities(int[] var1) {
		_node.setQuantities(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setItemIds(int[])
	 */
	@Override
	public void setItemIds(int[] var1) {
		_node.setItemIds(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setBoundsIndex(int)
	 */
	@Override
	public void setBoundsIndex(int var1) {
		_node.setBoundsIndex(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setActions(java.lang.String[])
	 */
	@Override
	public void setActions(String[] var1) {
		_node.setActions(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setUid(int)
	 */
	@Override
	public void setUid(int var1) {
		_node.setUid(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setName(java.lang.String)
	 */
	@Override
	public void setName(String var1) {
		_node.setName(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setText(java.lang.String)
	 */
	@Override
	public void setText(String var1) {
		_node.setText(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTextColor(int)
	 */
	@Override
	public void setTextColor(int var1) {
		_node.setTextColor(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTextAlpha(int)
	 */
	@Override
	public void setTextAlpha(int var1) {
		_node.setTextAlpha(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTextureId(int)
	 */
	@Override
	public void setTextureId(int var1) {
		_node.setTextureId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setBorderThickness(int)
	 */
	@Override
	public void setBorderThickness(int var1) {
		_node.setBorderThickness(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setModelType(int)
	 */
	@Override
	public void setModelType(int var1) {
		_node.setModelType(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setModelId(int)
	 */
	@Override
	public void setModelId(int var1) {
		_node.setModelId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRelativeX(int)
	 */
	@Override
	public void setRelativeX(int var1) {
		_node.setRelativeX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRelativeY(int)
	 */
	@Override
	public void setRelativeY(int var1) {
		_node.setRelativeY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setWidth(int)
	 */
	@Override
	public void setWidth(int var1) {
		_node.setWidth(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setHeight(int)
	 */
	@Override
	public void setHeight(int var1) {
		_node.setHeight(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setParentId(int)
	 */
	@Override
	public void setParentId(int var1) {
		_node.setParentId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setIndex(int)
	 */
	@Override
	public void setIndex(int var1) {
		_node.setIndex(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRotationX(int)
	 */
	@Override
	public void setRotationX(int var1) {
		_node.setRotationX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRotationY(int)
	 */
	@Override
	public void setRotationY(int var1) {
		_node.setRotationY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRotationZ(int)
	 */
	@Override
	public void setRotationZ(int var1) {
		_node.setRotationZ(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setHidden(boolean)
	 */
	@Override
	public void setHidden(boolean var1) {
		_node.setHidden(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setInsetX(int)
	 */
	@Override
	public void setInsetX(int var1) {
		_node.setInsetX(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setInsetY(int)
	 */
	@Override
	public void setInsetY(int var1) {
		_node.setInsetY(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setViewportWidth(int)
	 */
	@Override
	public void setViewportWidth(int var1) {
		_node.setViewportWidth(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setViewportHeight(int)
	 */
	@Override
	public void setViewportHeight(int var1) {
		_node.setViewportHeight(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setModelZoom(int)
	 */
	@Override
	public void setModelZoom(int var1) {
		_node.setModelZoom(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setSelectedAction(java.lang.String)
	 */
	@Override
	public void setSelectedAction(String var1) {
		_node.setSelectedAction(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setMouseEnterListener(java.lang.Object[])
	 */
	@Override
	public void setMouseEnterListener(Object[] var1) {
		_node.setMouseEnterListener(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setMouseExitListener(java.lang.Object[])
	 */
	@Override
	public void setMouseExitListener(Object[] var1) {
		_node.setMouseEnterListener(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setMouseHoverListener(java.lang.Object[])
	 */
	@Override
	public void setMouseHoverListener(Object[] var1) {
		_node.setMouseHoverListener(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setConfigListenerArgs(java.lang.Object[])
	 */
	@Override
	public void setConfigListenerArgs(Object[] var1) {
		_node.setConfigListenerArgs(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setConfigTriggers(int[])
	 */
	@Override
	public void setConfigTriggers(int[] var1) {
		_node.setConfigTriggers(var1);
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setRenderListener(java.lang.Object[])
	 */
	@Override
	public void setRenderListener(Object[] var1) {
		_node.setRenderListener(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTableListenerArgs(java.lang.Object[])
	 */
	@Override
	public void setTableListenerArgs(Object[] var1) {
		_node.setTableListenerArgs(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTableModTriggers(int[])
	 */
	@Override
	public void setTableModTriggers(int[] var1) {
		_node.setTableModTriggers(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setSkillListenerArgs(java.lang.Object[])
	 */
	@Override
	public void setSkillListenerArgs(Object[] var1) {
		_node.setSkillListenerArgs(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setSkillTriggers(int[])
	 */
	@Override
	public void setSkillTriggers(int[] var1) {
		_node.setSkillTriggers(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setScrollListeners(java.lang.Object[])
	 */
	@Override
	public void setScrollListeners(Object[] var1) {
		_node.setScrollListeners(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setFontId(int)
	 */
	@Override
	public void setFontId(int var1) {
		_node.setFontId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setTextShadowed(boolean)
	 */
	@Override
	public void setTextShadowed(boolean var1) {
		_node.setTextShadowed(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setShadowColour(int)
	 */
	@Override
	public void setShadowColour(int var1) {
		_node.setShadowColour(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setFlippedVertically(boolean)
	 */
	@Override
	public void setFlippedVertically(boolean var1) {
		_node.setFlippedVertically(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setFlippedHorizontally(boolean)
	 */
	@Override
	public void setFlippedHorizontally(boolean var1) {
		_node.setFlippedHorizontally(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setWidgetType(int)
	 */
	@Override
	public void setWidgetType(int var1) {
		_node.setWidgetType(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setItemId(int)
	 */
	@Override
	public void setItemId(int var1) {
		_node.setItemId(var1);		
	}

	/* (non-Javadoc)
	 * @see org.nullbool.piexternal.game.api.accessors.widgets.IWidget#setStackSize(int)
	 */
	@Override
	public void setStackSize(int var1) {
		_node.setStackSize(var1);		
	}
}