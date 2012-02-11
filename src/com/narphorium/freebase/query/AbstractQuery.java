package com.narphorium.freebase.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtree.json.JSONWriter;

import com.narphorium.freebase.results.ResultSet;

public abstract class AbstractQuery implements Query {

	private static final Log LOG = LogFactory.getLog(AbstractQuery.class);

	private String name;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<Parameter> blankFields = new ArrayList<Parameter>();
	private Map<String, Parameter> parametersByName = new HashMap<String, Parameter>();
	private Object data;
	private ResultSet resultSet;

	public AbstractQuery(final String name, final Object data,
			final List<Parameter> parameters, final List<Parameter> blankFields) {
		this.name = name;
		this.data = data;
		for (final Parameter parameter : parameters) {
			this.parameters.add(parameter);
			this.parametersByName.put(parameter.getName(), parameter);
		}

		this.blankFields.addAll(blankFields);
	}

	public AbstractQuery(final Query query) {
		this.name = query.getName();
		this.data = copyData(query.getData());
		for (final Parameter parameter : query.getParameters()) {
			this.parameters.add(parameter);
			this.parametersByName.put(parameter.getName(), new Parameter(
					parameter));
		}

		this.blankFields.addAll(query.getBlankFields());
	}

	@SuppressWarnings("unchecked")
	private Object copyData(final Object data) {
		if (data instanceof Map) {
			final Map<String, Object> mapData = (Map<String, Object>) data;
			final Map<String, Object> map = new HashMap<String, Object>();
			for (final String key : mapData.keySet()) {
				map.put(key, copyData(mapData.get(key)));
			}

			return map;
		} else if (data instanceof List) {
			final List<Object> listData = (List<Object>) data;
			final List<Object> list = new ArrayList<Object>();
			for (final Object element : listData) {
				list.add(copyData(element));
			}

			return list;
		} else {
			return data;
		}
	}

	public final String getName() {
		return name;
	}

	public final Object getData() {
		return data;
	}

	public final List<Parameter> getParameters() {
		return parameters;
	}

	public final boolean hasParameter(final String name) {
		return parametersByName.containsKey(name);
	}

	public final Parameter getParameter(final String name) {
		return parametersByName.get(name);
	}

	public final void resetParameters() {
		for (final Parameter parameter : parameters) {
			setParameterValue(parameter.getName(), parameter.getDefaultValue());
		}
	}

	@SuppressWarnings("unchecked")
	public final void setParameterValue(final String name, final Object value) {
		final Parameter parameter = parametersByName.get(name);
		if (parameter == null) {
			LOG.error("Parameter \"" + name + "\" does not exist.");
			return;
		}

		Object topData = data;
		if (topData instanceof List) {
			topData = ((List<Object>) topData).get(0);
		}
		parameter.getPath().setValue(topData, value);
		/*
		 * for (JsonPath path : parameter.getPaths()) { path.setValue(topData,
		 * value); }
		 */
	}

	public final List<Parameter> getBlankFields() {
		return blankFields;
	}

	public final ResultSet getResultSet() {
		return resultSet;
	}

	public final void setResultSet(final ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public final String toJSON() {
		final JSONWriter writer = new JSONWriter();
		String query = writer.write(data);
		query = query.replaceAll("\\\\/", "/");
		return query;
	}

}