package org.nullbool.pi.core.engine.api;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * @author Bibl (don't ban me pls)
 * @created 12 Jun 2015 19:48:01
 */
public abstract class AbstractVirtualGameBrowser implements IVirtualGameBrowser {

	private final transient Map<URL, WeakReference<Image>> imageCache;
	private final transient Map<String, InputStream> inputCache;
	private final IPageCrawler crawler;
	private volatile transient Applet appletRef;

	public AbstractVirtualGameBrowser(IPageCrawler crawler) {
		this.imageCache = new HashMap<URL, WeakReference<Image>>();
		this.inputCache = Collections.synchronizedMap(new HashMap<String, InputStream>(2));
		this.crawler = crawler;
		this.appletRef = null;
	}

	public AbstractVirtualGameBrowser(IPageCrawler crawler, Applet applet) {
		this.imageCache = new HashMap<URL, WeakReference<Image>>();
		this.inputCache = Collections.synchronizedMap(new HashMap<String, InputStream>(2));
		this.crawler = crawler;

		setApplet(applet);
	}

	@Override
	public abstract AudioClip getAudioClip(URL url);

	@Override
	public abstract void showDocument(URL url, String target);

	@Override
	public abstract void showStatus(String status);

	@Override
	public abstract URL getDocumentBase();

	@Override
	public abstract URL getCodeBase();

	@Override
	public String getParameter(String name) {
		String value = crawler.getGameParameters().get(name);
		if(value == null) {
			System.err.printf("Null parameter value for key=%s.%n", name);
			return "";
		} else {
			return value;
		}
	}

	@Override
	public synchronized void appletResize(int width, int height) {
		if(appletRef != null) {
			Dimension size = new Dimension(width, height);
			appletRef.setSize(size);
			appletRef.setPreferredSize(size);
		}
	}

	@Override
	public synchronized void setApplet(Applet applet) {
		this.appletRef = applet;
		applet.setStub(this);
	}

	@Override
	public Image getImage(URL url) {
		synchronized (imageCache) {
			WeakReference<Image> ref = imageCache.get(url);
			Image img;
			if ((ref == null) || ((img = ref.get()) == null)) {
				img = Toolkit.getDefaultToolkit().createImage(url);
				ref = new WeakReference<Image>(img);
				imageCache.put(url, ref);
			}
			return img;
		}
	}

	@Override
	public Applet getApplet(String name) {
		String thisName = getParameter("name");
		if (thisName == null)
			return null;
		return thisName.equals(name) ? appletRef : null;
	}

	@Override
	public Enumeration<Applet> getApplets() {
		Vector<Applet> applets = new Vector<Applet>();
		applets.add(appletRef);
		return applets.elements();
	}

	@Override
	public void showDocument(URL url) {
		showDocument(url, "");
	}

	@Override
	public void setStream(String key, InputStream stream) throws IOException {
		inputCache.put(key, stream);		
	}

	@Override
	public InputStream getStream(String key) {
		return inputCache.get(key);
	}

	@Override
	public Iterator<String> getStreamKeys() {
		return Collections.unmodifiableSet(inputCache.keySet()).iterator();
	}

	@Override
	public boolean isActive() {
		return appletRef != null;
	}

	@Override
	public AppletContext getAppletContext() {
		return this;
	}

	@Override
	public IPageCrawler getCrawler() {
		return crawler;
	}
}