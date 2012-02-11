package com.narphorium.freebase.query;

import java.util.List;

import com.narphorium.freebase.results.ResultSet;
import com.narphorium.freebase.services.ReadService;

public interface Query {
	Parameter getParameter(String name);

	void setParameterValue(String name, Object value);

	void parseParameterValue(String name, String value);

	String getName();

	void resetParameters();

	Object getData();

	List<Parameter> getParameters();

	List<Parameter> getBlankFields();

	ResultSet buildResultSet(ReadService readService);

	ResultSet getResultSet();

	void setResultSet(ResultSet resultSet);

	String toJSON();

	boolean hasParameter(String name);
}
