/**
 * 
 */
package org.nullbool.pi.core.engine.impl.factory.ext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.nullbool.pi.core.engine.api.transform.DefaultAPIHelper;
import org.nullbool.pi.core.engine.api.transform.IAPIHelper;
import org.nullbool.pi.core.engine.api.transform.TransformationEngine;
import org.nullbool.pi.core.engine.impl.factory.OldschoolContextFactory;
import org.nullbool.pi.core.engine.impl.transformers.TransformationEngineImpl;
import org.nullbool.pi.core.hook.api.HookMap;
import org.nullbool.pi.core.hook.api.serialisation.IMapDeserialiser;
import org.nullbool.pi.core.hook.api.serialisation.IMapSerialisationFactory;
import org.objectweb.asm.tree.ClassNode;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.topdank.byteengineer.commons.data.JarContents;
import org.topdank.byteio.out.NonMetaJarDumper;

import com.google.gson.Gson;

/**
 * @author Bibl (don't ban me pls)
 * @created 24 Jun 2015 01:10:53
 */
public class SimpleInjectionActor implements IActor {
	
	private final boolean force;
	
	public SimpleInjectionActor() {
		this(false);
	}
	
	public SimpleInjectionActor(boolean force) {
		this.force = force;
	}
	
	@Override
	public void act(JarContents<ClassNode> contents, File baseDir) throws Exception {
		File log = FileSet.LOG.getAbsoluteLocation(baseDir);
		File translation = FileSet.TRANSLATION.getAbsoluteLocation(baseDir);
		
		// TODO: Resolve
		
		if(!force) {
			return;
		}
		
		InputStream is = new FileInputStream(log);
		HookMap hooks = loadHooks(is);
		is.close();
		
		is = new FileInputStream(translation);
		IAPIHelper helper = loadMapping(is);
		is.close();
		
		try {
			transform(contents.getClassContents().namedMap(), hooks, helper);
		} catch (Throwable e) {
			throw new Exception("Error transforming classes.", e);
		}
		
		new NonMetaJarDumper(contents).dump(FileSet.TRANSFORMED.getAbsoluteLocation(baseDir));
	}
	
	public void transform(Map<String, ClassNode> classes, HookMap hooks, IAPIHelper helper) throws Throwable {
		TransformationEngine engine = new TransformationEngineImpl(classes, hooks, helper);
		engine.transformAll();
	}
	
	public IAPIHelper loadMapping(InputStream is) throws IOException {
		int avail = is.available();
		if(avail > 0) {
			byte[] bytes = new byte[avail];
			is.read(bytes, 0, bytes.length);
			String str = new String(bytes);
			IAPIHelper helper = new Gson().fromJson(str, DefaultAPIHelper.class);
			return helper;
		} else {
			throw new IOException("No data available.");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HookMap loadHooks(InputStream is) throws IOException {
		BundleContext context = FrameworkUtil.getBundle(OldschoolContextFactory.class).getBundleContext();

		int avail = is.available();
		if(avail > 0) {
			//byte[] bytes = new byte[avail];
			//is.read(bytes, 0, bytes.length);

			StringBuilder sb = new StringBuilder();
			
			// content type ends with '\n'
			int b;
			while((b = is.read()) != (int)'\n' && b != -1) {
				sb.append((char)b);
			}
			
			if(b == -1) {
				throw new IOException("Empty data.");
			}
			
			String str = sb.toString();
			
			if(str != null && str.startsWith(IMapSerialisationFactory.CONTENT_TYPE) && str.contains("=")) {
				// force active rather than static
				String strFilter = new StringBuilder("(").append(str).append(")").toString();
				try {
					// Filter filter = context.createFilter(strFilter);
					Collection<ServiceReference<IMapSerialisationFactory>> facSvcRefs = context.getServiceReferences(IMapSerialisationFactory.class, strFilter);
					if(facSvcRefs.size() < 1) {
						throw new IOException("No providers for " + strFilter);
					} else if(facSvcRefs.size() > 1) {
						System.err.println("MULTIPLE MAP SERIALISATION FACTORIES (" + facSvcRefs.size() + "), USING FIRST.");
					}
					
					ServiceReference<IMapSerialisationFactory> svcRef = facSvcRefs.iterator().next();
					IMapSerialisationFactory factory = context.getService(svcRef);
					IMapDeserialiser<HookMap> deserialiser = factory.createDeserialiser();
					HookMap map = deserialiser.deserialise(is);
					context.ungetService(svcRef);
					return map;
				} catch (InvalidSyntaxException e) {
					throw new IOException("Invalid " + IMapSerialisationFactory.CONTENT_TYPE + ", \"" + str + "\"" , e);
				}
			}
		} else {
			throw new IOException("No data available.");
		}
		
		return null;
	}
}