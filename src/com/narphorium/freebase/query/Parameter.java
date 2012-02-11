package com.narphorium.freebase.query;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

	public interface TypeConverter {
		Object convert(String data);
	}

	private static Map<String, TypeConverter> convertersByType = new HashMap<String, TypeConverter>();
	static {
		convertersByType.put("/type/text", new TypeConverter() {
			public Object convert(String data) {
				return data;
			}
		});
		/*
		 * convertersByType.put("/type/datetime", new Converter(){ private
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); public
		 * Object convert(String data) { try { return dateFormat.parse(data); }
		 * catch (ParseException e) {} return null; }});
		 */
		convertersByType.put("/type/float", new TypeConverter() {
			public Object convert(String data) {
				return Double.parseDouble(data);
			}
		});
		convertersByType.put("/type/integer", new TypeConverter() {
			public Object convert(String data) {
				return Long.parseLong(data);
			}
		});
		convertersByType.put("/type/boolean", new TypeConverter() {
			public Object convert(String data) {
				return Boolean.parseBoolean(data);
			}
		});
	}

	private String name;
	private String id;
	private String expectedType;
	private TypeConverter valueConverter;
	private Object defaultValue;
	private JsonPath path;

	// private List<JsonPath> paths = new ArrayList<JsonPath>();

	public Parameter(final String name, final String id,
			final Object defaultValue) {
		this.name = name;
		this.id = id;
		this.defaultValue = defaultValue;
	}

	public Parameter(final Parameter parameter) {
		this.name = parameter.name;
		this.id = parameter.id;
		this.expectedType = parameter.getExpectedType();
		this.valueConverter = parameter.getValueConverter();
		this.defaultValue = parameter.defaultValue;
		// this.paths.addAll(parameter.getPaths());
		this.path = parameter.path;
	}

	public final synchronized String getName() {
		return name;
	}

	/*
	 * public List<JsonPath> getPaths() { return paths; }
	 */

	public final JsonPath getPath() {
		return path;
	}

	public final Object getDefaultValue() {
		return defaultValue;
	}

	public final synchronized String getId() {
		return id;
	}

	public final synchronized String getKey() {
		return name + ":" + id;
	}

	public final synchronized Object parseValue(final String data) {
		return valueConverter.convert(data);
	}

	public final synchronized String getExpectedType() {
		return expectedType;
	}

	/*
	 * public void addPath(JsonPath path) { this.paths.add(path); }
	 */

	public final void setPath(JsonPath path) {
		this.path = path;
	}

	public final synchronized void setExpectedType(final String expectedType) {
		this.expectedType = expectedType;
		this.valueConverter = convertersByType.get(this.expectedType);
		if (this.valueConverter == null) {
			this.valueConverter = new TypeConverter() {
				public Object convert(final String data) {
					return data;
				}
			};
		}
	}

	private synchronized TypeConverter getValueConverter() {
		return this.valueConverter;
	}
}
