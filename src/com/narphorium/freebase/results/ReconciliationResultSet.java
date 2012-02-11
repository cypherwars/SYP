package com.narphorium.freebase.results;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReconciliationResultSet implements Iterable<ReconciliationResult> {

	private final List<ReconciliationResult> results = new ArrayList<ReconciliationResult>();

	public ReconciliationResultSet() {
	}

	public ReconciliationResultSet(final List<ReconciliationResult> results) {
		this.results.addAll(results);
	}

	public Iterator<ReconciliationResult> iterator() {
		return results.iterator();
	}
}
