package com.narphorium.freebase.query;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.narphorium.freebase.query.io.QueryParser;
import com.narphorium.freebase.results.Result;
import com.narphorium.freebase.results.ResultSet;
import com.narphorium.freebase.services.ReadService;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public class ParameterTypeResolver {

	private static final Log LOG = LogFactory
			.getLog(ParameterTypeResolver.class);
	private static Pattern parameterNamePattern = Pattern
			.compile("([\\d\\w_]+):([\\d\\w_]+)");
	private static Matcher parameterNameMatcher = parameterNamePattern
			.matcher("");

	private static Map<String, String> objectProperties = new HashMap<String, String>();
	static {
		objectProperties.put("id", "/type/id");
		objectProperties.put("guid", "/type/id");
		objectProperties.put("type", "/type/type");
		objectProperties.put("name", "/type/text");
		objectProperties.put("key", "/type/key");
		objectProperties.put("timestamp", "/type/datetime");
		objectProperties.put("permission", "/type/permission");
		objectProperties.put("creator", "/type/user");
		objectProperties.put("attribution", "/type/attribution");
	}

	private static Set<String> mqlReservedWords = new HashSet<String>();
	static {
		mqlReservedWords.add("return");
		mqlReservedWords.add("count");
		mqlReservedWords.add("connect");
		mqlReservedWords.add("create");
		mqlReservedWords.add("delete");
		mqlReservedWords.add("limit");
		mqlReservedWords.add("sort");
		mqlReservedWords.add("value");
		mqlReservedWords.add("lang");
		mqlReservedWords.add("namespace");
		mqlReservedWords.add("timestamp");
		mqlReservedWords.add("cursor");
	}

	private static Map<String, String> expectedTypeByProperty = new HashMap<String, String>();
	private Query expectedTypeQuery;

	private ReadService readService;

	public ParameterTypeResolver(final ReadService readService) {
		this.readService = readService;
		this.expectedTypeQuery = new QueryParser()
				.parse("q1",
						"{\"property_id:id\":null,\"type\":\"/type/property\",\"expected_type:expected_type\":null}");
	}

	public final void process(final Query query) {
		processData(query, query.getData(), "/type/object");
	}

	@SuppressWarnings("unchecked")
	private void processData(final Query query, final Object data,
			final String expectedType) {
		String ept = expectedType;

		if (data == null) {
			return;
		} else if (data instanceof List) {
			for (Object element : (List<Object>) data) {
				processData(query, element, ept);
			}
		} else if (data instanceof Map) {
			Map<String, Object> mapData = (Map<String, Object>) data;
			if (mapData.containsKey("type")) {
				Object type = mapData.get("type");
				if (type instanceof String) {
					ept = (String) type;
				} else if (type instanceof Map) {
					ept = ((Map<String, String>) type).get("id");
				}
			}
			for (String key : mapData.keySet()) {
				Object value = mapData.get(key);
				String childExpectedType = "/type/object";

				parameterNameMatcher.reset(key);
				if (parameterNameMatcher.matches()) {
					String name = parameterNameMatcher.group(1);
					String id = parameterNameMatcher.group(2);
					childExpectedType = lookupExpectedType(id, ept);
					Parameter parameter = query.getParameter(name);
					if (parameter != null) {
						parameter.setExpectedType(childExpectedType);
					}
				} else if (!mqlReservedWords.contains(key)) {
					childExpectedType = lookupExpectedType(key, ept);
				}
				processData(query, value, childExpectedType);
			}
		}
	}

	private String lookupExpectedType(String id, String parentType) {
		if (objectProperties.keySet().contains(id)) {
			return objectProperties.get(id);
		} else if (id.equals("value")) {
			return parentType;
		}
		String property = id;
		if (!property.matches("/[\\w\\d_]+/[\\w\\d_]+/[\\w\\d_]+")) {
			property = parentType + "/" + id;
		}
		String expectedType = expectedTypeByProperty.get(property);
		if (expectedType == null) {
			expectedTypeQuery.setParameterValue("property_id", property);
			try {
				ResultSet results = readService.read(expectedTypeQuery);
				Result result = results.next();
				if (result != null) {
					expectedType = result.getString("expected_type");
					expectedTypeByProperty.put(property, expectedType);
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			} catch (FreebaseServiceException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return expectedType;
	}

}
