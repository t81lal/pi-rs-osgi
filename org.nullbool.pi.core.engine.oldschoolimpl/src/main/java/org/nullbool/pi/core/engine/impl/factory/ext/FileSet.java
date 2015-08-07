package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bibl (don't ban me pls)
 * @created 5 Jun 2015 09:25:00
 */
// this doesn't feel like a good idea. -Bibl
public enum FileSet {
	// TODO: Fix custom jars
	PAPI        (new Builder(-1).relativeName("papi.jar").optional(true)),
	API         (new Builder(1).relativeName("api.jar").actor(new InstallerActor())),
	LOG         (new Builder(2).relativeName("log.ser")),
	TRANSLATION (new Builder(3).relativeName("translate.json")),
	REFACTOR    (new Builder(4).relativeName("refactor.jar").priority(5).runnable(true).actor(new SimpleInjectionActor(true))),
	DEOB        (new Builder(5).relativeName("deob.jar").priority(4).runnable(true).require(-1, 1, 2, 3).actor(new SimpleInjectionActor(true))),
	/* Highest */
	TRANSFORMED (new Builder(6).relativeName("transformed.jar").priority(10).runnable(true).actor(new InstallerActor())),
	VANILLA     (new Builder(7).relativeName("vanilla.jar").priority(2).runnable(true).require(-1, 1, 2, 3).actor(new SimpleInjectionActor(true)));
	
	private static final Comparator<FileSet> PRIORITY_COMPARATOR = new Comparator<FileSet>() {
		
		@Override
		public int compare(FileSet f1, FileSet f2) {
			int o1 = f1.priority;
			int o2 = f2.priority;
			
			/* Highest to lowest */
			if(o1 < o2)
				return 1;
			if(o1 > o2)
				return -1;
			return 0;
		}
	};
	
	private final int id;
	private final String relativeName;
	private final int priority;
	private final boolean runnable;
	private final List<Integer> requires;
	private final boolean optional;
	private final IActor<?> actor;
	
	private FileSet(Builder builder) {
		id = builder.getId();
		relativeName = builder.getRelativeName();
		priority = builder.getPriority();
		runnable = builder.isRunnable();
		optional = builder.isOptional();
		requires = builder.getRequire();
		actor = builder.getActor();
	}
	
	public int id() {
		return id;
	}
	
	public int priority() {
		return priority;
	}
	
	public File getAbsoluteLocation(File dir) {
		return new File(dir, relativeName);
	}
	
	public URL getRemoteLocation(URL baseURL) {
		try {
			return new URL(baseURL.toExternalForm() + "/" + relativeName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean runnable() {
		return runnable;
	}
	
	public List<Integer> requires() {
		return requires;
	}
	
	public boolean resolved(Collection<FileSet> fss) {
		for(int id : requires) {
			FileSet fs = byId(id);
			if(fs == null)
				return false;
			if(!fss.contains(fs))
				return false;
		}
		/*for(FileSet f : fs) {
			if(!requires.contains(f.id) && f.id != id) {
				return false;
			}
		}*/
		return true;
	}
	
	public IActor<?> actor() {
		return actor;
	}
	
	@Deprecated
	public boolean isReachable(URL baseURL) {
		return getRemoteLocation(baseURL) != null;
	}
	
	public boolean optional() {
		return optional;
	}
	
	public static FileSet byId(int id) {
		for(FileSet value : values()) {
			if(value.id == id)
				return value;
		}
		return null;
	}
	
	public static List<FileSet> existing(File dir) {
		List<FileSet> list = new ArrayList<FileSet>();
		for(FileSet value : values()) {
			if(value.getAbsoluteLocation(dir).exists()) {
				list.add(value);
			}
		}
		
		System.out.println("fs; " + list);
		Collections.sort(list, PRIORITY_COMPARATOR);
		return list;
	}
	
	public static Set<FileSet> missing(Collection<FileSet> set) {
		Set<FileSet> newSet = new HashSet<FileSet>();
		for(FileSet value : values()) {
			if(!set.contains(value) && value.priority == -1)
				newSet.add(value);
		}
		return newSet;
	}

	public String relativeName() {
		return relativeName;
	}
	
	@Override
	public String toString() {
		return "FileSet[id=" + id + ", relname=" + relativeName + "]";
	}
}