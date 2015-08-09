/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
 * Loose specification inheriting from {@link AppletStub} and {@link AppletContext}
 * for simulating the browser-applet relationship, where the Applet is able to request
 * resources such as Images and AudioClips. Stream control and simple output is
 * implementation specific and so although this interface is supposed to emulate a
 * browser (in the loose sense of the word), some features may not be accessible.
 * 
 * @see java.applet.Applet
 * @see java.applet.AppletStub
 * @see java.applet.AppletContext
 * @see java.applet.AudioClip
 * @see java.awt.Image
 * 
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:27:33
 */
public interface IVirtualGameBrowser extends AppletStub, AppletContext {

	/**
	 * Syncs an Applet with this browser instance. <br>
	 * Syncing in this case may refer to caching or calls to {@link Applet#setStub(AppletStub)}.
	 * 
	 * @param applet The new Applet.
	 */
	public void setApplet(Applet applet);
	
	@Override
	public AudioClip getAudioClip(URL url);

	@Override
	public Image getImage(URL url);

	@Override
	public Applet getApplet(String name);

	@Override
	public Enumeration<Applet> getApplets();

	@Override
	public void showDocument(URL url);

	@Override
	public void showDocument(URL url, String target);

	@Override
	public void showStatus(String status);

	@Override
	public void setStream(String key, InputStream stream)throws IOException;

	@Override
	public InputStream getStream(String key);

	@Override
	public Iterator<String> getStreamKeys();

	@Override
	public boolean isActive();

	@Override
	public URL getDocumentBase();

	@Override
	public URL getCodeBase();

	@Override
	public String getParameter(String name);

	/**
	 * Each virtual game browser is associated with a webpage which embeds the
	 * Applet that is going to be ran. Therefore a {@link IPageCrawler} is
	 * needed to parse the parameters for the Applet environment and the
	 *  Applet itself.
	 * 
	 * @return The crawler.
	 */
	public IPageCrawler getCrawler();
	
	@Override
	public AppletContext getAppletContext();

	@Override
	public void appletResize(int width, int height);
}
