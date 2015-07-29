package org.nullbool.piexternal.game.api.accessors.world;

import org.nullbool.piexternal.game.api.accessors.entity.IRenderable;

public interface IGameObject extends ISceneComponent {
	int getHash();

	void setHash(int var1);

	int getPlane();

	void setPlane(int var1);

	int getStrictX();

	void setStrictX(int var1);

	int getStrictY();

	void setStrictY(int var1);

	int getLocalX();

	void setLocalX(int var1);

	int getLocalY();

	void setLocalY(int var1);

	int getWidth();

	void setWidth(int var1);

	int getHeight();

	void setHeight(int var1);

	int getOrientation();

	void setOrientation(int var1);

	int getFlags();

	void setFlags(int var1);

	IRenderable getMarkedRenderable();
}
