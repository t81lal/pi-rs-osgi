package org.nullbool.pi.core.engine.api;

import org.nullbool.piexternal.game.api.IGameClient;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:25:34
 */
public abstract interface IContextFactory<T extends IClientContext<IGameClient>> {

	public abstract T create(IVirtualGameBrowser browser) throws Exception;
}