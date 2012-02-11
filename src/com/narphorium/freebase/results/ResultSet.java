package com.narphorium.freebase.results;

import com.narphorium.freebase.query.Query;
import com.narphorium.freebase.services.ReadService;
import com.narphorium.freebase.services.exceptions.FreebaseServiceException;

public interface ResultSet {
	int size() throws FreebaseServiceException;

	boolean isEmpty();

	boolean hasNext() throws FreebaseServiceException;

	Result next() throws FreebaseServiceException;

	Result current();

	void reset();

	ReadService getReadService();

	Query getQuery();
}
