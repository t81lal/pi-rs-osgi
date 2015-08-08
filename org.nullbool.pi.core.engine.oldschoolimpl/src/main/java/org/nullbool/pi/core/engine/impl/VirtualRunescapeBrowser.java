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

package org.nullbool.pi.core.engine.impl;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.nullbool.pi.core.engine.api.AbstractVirtualGameBrowser;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 20:02:31
 */
public class VirtualRunescapeBrowser extends AbstractVirtualGameBrowser {

	private static final Dimension DEFAULT_DIMENSION = new Dimension(765, 503);
	
	private final URL pageURL;
	private final URL gamepackURL;

	public VirtualRunescapeBrowser(URL pageURL) throws IOException {
		super(new OldschoolCrawler(pageURL));
		this.pageURL = pageURL;
		if(!getCrawler().getAppletParameters().containsKey("archive"))
			throw new IOException("No archive.");
		
		this.gamepackURL = new URL(pageURL.toExternalForm() + getCrawler().getAppletParameters().get("archive"));
	}

	@Override
	public synchronized void setApplet(Applet applet) {
		if(applet == null)
			return;
		
		super.setApplet(applet);
		
		Map<String, String> params = getCrawler().getAppletParameters();
		int newWidth = asInt(params, "width", DEFAULT_DIMENSION.width);
		int newHeight = asInt(params, "height", DEFAULT_DIMENSION.height);
	
		applet.setPreferredSize(new Dimension(newWidth, newHeight));		
		applet.setSize(new Dimension(newWidth, newHeight));
		
		//appletResize(newHeight, newWidth);
	}
	
	private static int asInt(Map<String, String> params, String key, int val) {
		if(!params.containsKey(key))
			return val;
		
		String p = params.get(key);
		try {
			if(p.endsWith("%"))
				p = p.substring(0, p.length() - 1);
			int ip = Integer.parseInt(p);
			if(ip <= 0 /* || ip > 100*/) {
				System.err.printf("Applet %s = %d?.%n", key, ip);
				return val;
			} else {
				return val * (ip / 100);
			}
		} catch(NumberFormatException e) {
			System.err.printf("Map value for %s wasn't a valid int.%n", key);
			e.printStackTrace();
			return val;
		} catch(NullPointerException e) {
			System.err.printf("Map value for %s was null.%n", key);
			e.printStackTrace();
			return val;
		}
	}

	@Override
	public AudioClip getAudioClip(URL url) {
		return null;
	}

	@Override
	public void showDocument(URL url, String target) {
		System.out.printf("Applet at [%s] attempting to show %s.%n", url, target);
	}

	@Override
	public void showStatus(String status) {
		System.out.printf("Applet status: %s.%n", status);
	}

	@Override
	public URL getDocumentBase() {
		return pageURL;
	}

	@Override
	public URL getCodeBase() {
		return pageURL;
	}

	public URL getGamepackURL() {
		return gamepackURL;
	}
}
