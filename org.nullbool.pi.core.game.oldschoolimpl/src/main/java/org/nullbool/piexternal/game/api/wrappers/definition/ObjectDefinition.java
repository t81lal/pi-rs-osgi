package org.nullbool.piexternal.game.api.wrappers.definition;

import org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition;
import org.nullbool.piexternal.game.api.wrappers.collection.DualNode;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 19 Apr 2015 at 22:06:54 <br>
 */
public class ObjectDefinition extends DualNode<IObjectDefinition> implements IObjectDefinition {

	public ObjectDefinition(IObjectDefinition _objectdef) {
		super(_objectdef);
	}

	@Override
	public String[] getActions() {
		return getActions();
	}

	@Override
	public int getAnimationId() {
		return getAnimationId();
	}

	@Override
	public int getHeight() {
		return getHeight();
	}

	@Override
	public int getIcon() {
		return getIcon();
	}

	@Override
	public int getModelBreadth() {
		return getModelBreadth();
	}

	@Override
	public int getModelHeight() {
		return getModelHeight();
	}

	@Override
	public int getModelWidth() {
		return getModelWidth();
	}

	@Override
	public String getName() {
		return getName();
	}

	@Override
	public int getObjMapScene() {
		return getObjMapScene();
	}

	@Override
	public int getTranslationX() {
		return getTranslationX();
	}

	@Override
	public int getTranslationY() {
		return getTranslationY();
	}

	@Override
	public int getTranslationZ() {
		return getTranslationZ();
	}

	@Override
	public int getWidth() {
		return getWidth();
	}

	@Override
	public boolean hasCastedShadow() {
		return hasCastedShadow();
	}

	@Override
	public boolean isRotated() {
		return isRotated();
	}

	@Override
	public boolean isWalkable() {
		return isWalkable();
	}
}