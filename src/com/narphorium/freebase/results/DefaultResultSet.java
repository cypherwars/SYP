package com.narphorium.freebase.results;

import com.narphorium.freebase.query.Query;
import com.narphorium.freebase.services.ReadService;

public class DefaultResultSet extends AbstractResultSet {

	public DefaultResultSet(final Query query, final ReadService readService) {
		super(query, readService);
	}

}
