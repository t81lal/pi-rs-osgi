package org.nullbool.pi.core.scripting.api.loader;

import java.util.Arrays;

public class ExternalResourceDefinition {

	private final int priority;
	private final String type; //script or task
	private final String[] api_keys;
	private final String klassName;
	private final String name;
	private final String description;
	private final String version;
	private final String[] authors;

	public ExternalResourceDefinition(int priority, String type, String[] api_keys, String klassName, String name, String description, String version,
			String[] authors) {
		this.priority = priority;
		this.type = type;
		this.api_keys = api_keys;
		this.klassName = klassName;
		this.name = name;
		this.description = description;
		this.version = version;
		this.authors = authors;
	}

	public int getPriority() {
		return priority;
	}

	public String getType() {
		return type;
	}

	public String[] getApiKeys() {
		return api_keys;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(api_keys);
		result = prime * result + Arrays.hashCode(authors);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((klassName == null) ? 0 : klassName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priority;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExternalResourceDefinition other = (ExternalResourceDefinition) obj;
		if (!Arrays.equals(api_keys, other.api_keys))
			return false;
		if (!Arrays.equals(authors, other.authors))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (klassName == null) {
			if (other.klassName != null)
				return false;
		} else if (!klassName.equals(other.klassName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExternalResourceDefinition [priority=" + priority + ", type=" + type + ", api_keys=" + Arrays.toString(api_keys) + ", klassName="
				+ klassName + ", name=" + name + ", description=" + description + ", version=" + version + ", authors=" + Arrays.toString(authors)
				+ "]";
	}
}