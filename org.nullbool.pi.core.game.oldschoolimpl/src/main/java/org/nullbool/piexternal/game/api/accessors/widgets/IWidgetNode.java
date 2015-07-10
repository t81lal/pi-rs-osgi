package org.nullbool.piexternal.game.api.accessors.widgets;

import org.nullbool.piexternal.game.api.accessors.collections.INode;

public interface IWidgetNode extends INode {
   int getWidgetId();

   void setWidgetId(int var1);

   int getType();

   void setType(int var1);
}
