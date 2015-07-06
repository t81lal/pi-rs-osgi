package org.nullbool.piexternal.game.api.accessors.widgets;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IWidget extends INode {
   int[] getQuantities();

   int[] getItemIds();

   int getBoundsIndex();

   String[] getActions();

   int getUID();

   String getName();

   String getText();

   int getTextColor();

   int getTextAlpha();

   int getTextureId();

   int getBorderThickness();

   int getModelType();

   int getModelId();

   int getRelativeX();

   int getRelativeY();

   int getWidth();

   int getHeight();

   int getParentId();

   int getIndex();

   int getRotationX();

   int getRotationY();

   int getRotationZ();

   boolean isHidden();

   int getInsetX();

   int getInsetY();

   int getViewportWidth();

   int getViewportHeight();

   int getModelZoom();

   IWidget getParent();

   String getSelectedAction();

   int getButtonType();

   Object[] getMouseEnterListener();

   Object[] getMouseExitListener();

   Object[] getMouseHoverListener();

   Object[] getConfigListenerArgs();

   int[] getConfigTriggers();

   Object[] getRenderListener();

   Object[] getTableListenerArgs();

   int[] getTableModTriggers();

   Object[] getSkillListenerArgs();

   int[] getSkillTriggers();

   Object[] getScrollListeners();

   int getFontId();

   boolean isTextShadowed();

   int getShadowColour();

   boolean isFlippedVertically();

   boolean isFlippedHorizontally();

   IWidget[] getChildren();

   int getWidgetType();

   int getItemId();

   int getStackSize();
}
