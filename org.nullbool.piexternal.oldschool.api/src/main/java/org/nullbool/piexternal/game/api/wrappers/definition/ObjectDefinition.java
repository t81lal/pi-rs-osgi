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

	public IObjectDefinition instance() {
		return _node;
	}

	@Override
	public String[] getActions() {
		return _node.getActions();
	}

	@Override
	public int getAnimationId() {
		return _node.getAnimationId();
	}

	@Override
	public int getHeight() {
		return _node.getHeight();
	}

	@Override
	public int getIcon() {
		return _node.getIcon();
	}

	@Override
	public int getModelBreadth() {
		return _node.getModelBreadth();
	}

	@Override
	public int getModelHeight() {
		return _node.getModelHeight();
	}

	@Override
	public int getModelWidth() {
		return _node.getModelWidth();
	}

	@Override
	public String getName() {
		return _node.getName();
	}

	@Override
	public int getObjMapScene() {
		return _node.getObjMapScene();
	}

	@Override
	public int getTranslationX() {
		return _node.getTranslationX();
	}

	@Override
	public int getTranslationY() {
		return _node.getTranslationY();
	}

	@Override
	public int getTranslationZ() {
		return _node.getTranslationZ();
	}

	@Override
	public int getWidth() {
		return _node.getWidth();
	}

	@Override
	public boolean hasCastedShadow() {
		return _node.hasCastedShadow();
	}

	@Override
	public boolean isRotated() {
		return _node.isRotated();
	}

	@Override
	public boolean isWalkable() {
		return _node.isWalkable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setName(java.lang.String)
	 */
	@Override
	public void setName(String var1) {
		_node.setName(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setWidth(int)
	 */
	@Override
	public void setWidth(int var1) {
		_node.setWidth(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setHeight(int)
	 */
	@Override
	public void setHeight(int var1) {
		_node.setHeight(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setAnimationId(int)
	 */
	@Override
	public void setAnimationId(int var1) {
		_node.setAnimationId(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setObjMapScene(int)
	 */
	@Override
	public void setObjMapScene(int var1) {
		_node.setObjMapScene(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setModelWidth(int)
	 */
	@Override
	public void setModelWidth(int var1) {
		_node.setModelWidth(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setModelHeight(int)
	 */
	@Override
	public void setModelHeight(int var1) {
		_node.setModelHeight(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setModelBreadth(int)
	 */
	@Override
	public void setModelBreadth(int var1) {
		_node.setModelBreadth(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setTranslationX(int)
	 */
	@Override
	public void setTranslationX(int var1) {
		_node.setTranslationX(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setTranslationY(int)
	 */
	@Override
	public void setTranslationY(int var1) {
		_node.setTranslationY(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setTranslationZ(int)
	 */
	@Override
	public void setTranslationZ(int var1) {
		_node.setTranslationZ(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setWalkable(boolean)
	 */
	@Override
	public void setWalkable(boolean var1) {
		_node.setWalkable(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setActions(java.lang.String[])
	 */
	@Override
	public void setActions(String[] var1) {
		_node.setActions(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setIcon(int)
	 */
	@Override
	public void setIcon(int var1) {
		_node.setIcon(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setRotated(boolean)
	 */
	@Override
	public void setRotated(boolean var1) {
		_node.setRotated(var1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.nullbool.piexternal.game.api.accessors.definitions.IObjectDefinition
	 * #setHasCastedShadow(boolean)
	 */
	@Override
	public void setHasCastedShadow(boolean var1) {
		_node.setHasCastedShadow(var1);
	}
}