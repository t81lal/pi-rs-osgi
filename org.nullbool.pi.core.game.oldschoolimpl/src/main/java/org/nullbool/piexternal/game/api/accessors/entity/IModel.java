package org.nullbool.piexternal.game.api.accessors.entity;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IModel extends IRenderable {
   int getVertexCount();

   int getIndicesCount();

   int getTriangleCount();

   int[] getVerticesX();

   int[] getVerticesY();

   int[] getVerticesZ();

   int[] getIndicesX();

   int[] getIndicesY();

   int[] getIndicesZ();
}
