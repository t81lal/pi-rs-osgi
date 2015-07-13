package org.nullbool.piexternal.game.api.accessors.widgets;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IWidget extends INode {
   int[] getQuantities();

   void setQuantities(int[] var1);

   int[] getItemIds();

   void setItemIds(int[] var1);

   int getBoundsIndex();

   void setBoundsIndex(int var1);

   String[] getActions();

   void setActions(String[] var1);

   int getUid();

   void setUid(int var1);

   String getName();

   void setName(String var1);

   String getText();

   void setText(String var1);

   int getTextColor();

   void setTextColor(int var1);

   int getTextAlpha();

   void setTextAlpha(int var1);

   int getTextureId();

   void setTextureId(int var1);

   int getBorderThickness();

   void setBorderThickness(int var1);

   int getModelType();

   void setModelType(int var1);

   int getModelId();

   void setModelId(int var1);

   int getRelativeX();

   void setRelativeX(int var1);

   int getRelativeY();

   void setRelativeY(int var1);

   int getWidth();

   void setWidth(int var1);

   int getHeight();

   void setHeight(int var1);

   int getParentId();

   void setParentId(int var1);

   int getIndex();

   void setIndex(int var1);

   int getRotationX();

   void setRotationX(int var1);

   int getRotationY();

   void setRotationY(int var1);

   int getRotationZ();

   void setRotationZ(int var1);

   boolean isHidden();

   void setHidden(boolean var1);

   int getInsetX();

   void setInsetX(int var1);

   int getInsetY();

   void setInsetY(int var1);

   int getViewportWidth();

   void setViewportWidth(int var1);

   int getViewportHeight();

   void setViewportHeight(int var1);

   int getModelZoom();

   void setModelZoom(int var1);

   IWidget getParent();

   String getSelectedAction();

   void setSelectedAction(String var1);

   Object[] getMouseEnterListener();

   void setMouseEnterListener(Object[] var1);

   Object[] getMouseExitListener();

   void setMouseExitListener(Object[] var1);

   Object[] getMouseHoverListener();

   void setMouseHoverListener(Object[] var1);

   Object[] getConfigListenerArgs();

   void setConfigListenerArgs(Object[] var1);

   int[] getConfigTriggers();

   void setConfigTriggers(int[] var1);

   Object[] getRenderListener();

   void setRenderListener(Object[] var1);

   Object[] getTableListenerArgs();

   void setTableListenerArgs(Object[] var1);

   int[] getTableModTriggers();

   void setTableModTriggers(int[] var1);

   Object[] getSkillListenerArgs();

   void setSkillListenerArgs(Object[] var1);

   int[] getSkillTriggers();

   void setSkillTriggers(int[] var1);

   Object[] getScrollListeners();

   void setScrollListeners(Object[] var1);

   int getFontId();

   void setFontId(int var1);

   boolean isTextShadowed();

   void setTextShadowed(boolean var1);

   int getShadowColour();

   void setShadowColour(int var1);

   boolean isFlippedVertically();

   void setFlippedVertically(boolean var1);

   boolean isFlippedHorizontally();

   void setFlippedHorizontally(boolean var1);

   IWidget[] getChildren();

   int getWidgetType();

   void setWidgetType(int var1);

   int getItemId();

   void setItemId(int var1);

   int getStackSize();

   void setStackSize(int var1);
}
