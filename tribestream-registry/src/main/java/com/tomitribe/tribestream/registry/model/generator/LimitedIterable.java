/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2014
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.tribestream.registry.model.generator;

import java.util.Iterator;

public class LimitedIterable<T> implements Iterable<T> {

    private final int limit;
    private final Iterable<T> iterable;

    public LimitedIterable(final Iterable<T> iterable, final int limit) {
        this.limit = limit;
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        final Iterator<T> iterator = iterable.iterator();

        return new Iterator<T>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return iterator.hasNext() && count++ < limit;
            }

            @Override
            public T next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
}
