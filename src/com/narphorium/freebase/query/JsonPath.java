package com.narphorium.freebase.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPath {

	private final List<Object> elements = new ArrayList<Object>();

	public JsonPath() {
	}

	// TODO Add parsing constructor
	/*
	 * public JsonPath(String text) {
	 * 
	 * }
	 */

	public JsonPath(final JsonPath path) {
		this.elements.addAll(path.elements);
	}

	public final void addElement(final Object element) {
		elements.add(element);
	}

	@SuppressWarnings("unchecked")
	public final Object getValue(final Object data) {
		Object d = data;
		final List<Object> parameterResults = new ArrayList<Object>();

		boolean found = true;
		for (final Object key : elements) {
			if (key instanceof String && d instanceof Map) {
				d = ((Map<String, Object>) d).get((String) key);
			} else if (key instanceof Integer && d instanceof List) {
				d = ((List<Object>) d).get((Integer) key);
			} else {
				found = false;
			}
		}

		if (found) {
			parameterResults.add(d);
		}

		return parameterResults.size() == 1 ? parameterResults.get(0)
				: parameterResults;
	}

	@SuppressWarnings("unchecked")
	public final void setValue(final Object data, final Object value) {
		Object d = data;

		for (int k = 0; k < elements.size(); ++k) {
			final Object key = elements.get(k);
			if (key instanceof String) {
				if (k < elements.size() - 1) {
					d = ((Map<String, Object>) d).get((String) key);
				} else {
					((Map<String, Object>) d).put((String) key, value);
				}
			} else if (key instanceof Integer) {
				d = ((List<Object>) d).get((Integer) key);
			}
		}
	}

	public final String toString() {
		final StringBuffer path = new StringBuffer();
		for (final Object element : elements) {
			if (element instanceof Integer) {
				path.append('[');
				path.append(element);
				path.append(']');
			} else {
				if (path.length() > 0) {
					path.append(".");
				}

				path.append(element);
			}
		}

		return path.toString();
	}
}
