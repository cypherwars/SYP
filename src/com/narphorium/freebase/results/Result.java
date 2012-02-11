package com.narphorium.freebase.results;

import java.util.Date;
import java.util.List;

import com.narphorium.freebase.query.JsonPath;
import com.narphorium.freebase.query.Query;

public interface Result {
	Object getObject(JsonPath path);

	Object getObject(String variable);

	String getString(String variable);

	boolean getBoolean(String variable);

	int getInteger(String variable);

	float getFloat(String variable);

	Date getDate(String variable);

	List<Object> getCollection(String variable);

	Query getQuery();
}
