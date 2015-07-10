package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IModel extends IRenderable {
   int getVertexCount();

   void setVertexCount(int var1);

   int getIndicesCount();

   void setIndicesCount(int var1);

   int getTriangleCount();

   void setTriangleCount(int var1);

   int[] getVerticesX();

   void setVerticesX(int[] var1);

   int[] getVerticesY();

   void setVerticesY(int[] var1);

   int[] getVerticesZ();

   void setVerticesZ(int[] var1);

   int[] getIndicesX();

   void setIndicesX(int[] var1);

   int[] getIndicesY();

   void setIndicesY(int[] var1);

   int[] getIndicesZ();

   void setIndicesZ(int[] var1);
}
