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

public class FluentIterable<T> implements Iterable<T> {

    private Iterable<T> iterable;

    public FluentIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    public FluentIterable<T> limit(int i) {
        this.iterable = limit(this.iterable, i);
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    public static <T> FluentIterable<T> fluent(final Iterable<T> iterable) {
        return new FluentIterable<T>(iterable);
    }

    public static <T> FluentIterable<T> get(final Iterable<T> iterable) {
        return new FluentIterable<T>(iterable);
    }

    public static <T> Iterable<T> limit(Iterable<T> iterable, int i) {
        return new LimitedIterable<T>(iterable, i);
    }


}
