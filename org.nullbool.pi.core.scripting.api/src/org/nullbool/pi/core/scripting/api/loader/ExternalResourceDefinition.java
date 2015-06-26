package org.nullbool.pi.core.scripting.api.loader;

public class ExternalResourceDefinition {

	private final int priority;
	private final String klassName;
	private final String name;
	private final String description;
	private final String version;
	private final String[] authors;

	public ExternalResourceDefinition(int priority, String klassName, String name, String description, String version, String[] authors) {
		this.priority = priority;
		this.klassName = klassName;
		this.name = name;
		this.description = description;
		this.version = version;
		this.authors = authors;
	}

	public int getPriority() {
		return priority;
	}

	public String getKlassName() {
		return klassName;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}

	public String[] getAuthors() {
		return authors;
	}
}