package org.nullbool.pi.core.engine.api;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:27:33
 */
public abstract interface IVirtualGameBrowser extends AppletStub, AppletContext {

	public abstract void setApplet(Applet applet);
	
	@Override
	public abstract AudioClip getAudioClip(URL url);

	@Override
	public abstract Image getImage(URL url);

	@Override
	public abstract Applet getApplet(String name);

	@Override
	public abstract Enumeration<Applet> getApplets();

	@Override
	public abstract void showDocument(URL url);

	@Override
	public abstract void showDocument(URL url, String target);

	@Override
	public abstract void showStatus(String status);

	@Override
	public abstract void setStream(String key, InputStream stream)throws IOException;

	@Override
	public abstract InputStream getStream(String key);

	@Override
	public abstract Iterator<String> getStreamKeys();

	@Override
	public abstract boolean isActive();

	@Override
	public abstract URL getDocumentBase();

	@Override
	public abstract URL getCodeBase();

	@Override
	public abstract String getParameter(String name);

	public abstract IPageCrawler getCrawler();
	
	@Override
	public abstract AppletContext getAppletContext();

	@Override
	public abstract void appletResize(int width, int height);
}