package org.nullbool.pi.core.scripting.api.loader.finder;

import java.io.File;
import java.util.List;

import org.topdank.byteengineer.commons.data.JarInfo;

/**
 * @author Bibl (don't ban me pls) <br>
 * @created 24 Apr 2015 at 21:56:35 <br>
 */
public class JarInfoFolderSearchFinderStrategy extends FolderSearchFinderStrategy<JarInfo> {

	public JarInfoFolderSearchFinderStrategy(boolean deepSearch, File... dirs) {
		super(deepSearch, dirs);
	}

	public JarInfoFolderSearchFinderStrategy(boolean deepSearch, List<File> directories) {
		super(deepSearch, directories);
	}

	@Override
	protected JarInfo create(File f) {
		return new JarInfo(f);
	}

	@Override
	protected boolean accept(File f) {
		return f.getName().endsWith(".jar");
	}
}