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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Randoms {

    private static final Random random = new Random();

    public static <T> T selection(T... array) {
        return array[random.nextInt(array.length)];
    }

    public static <T> Iterable<T> iterate(T... array) {
        final List<T> list = new ArrayList<T>();
        list.addAll(Arrays.asList(array));
        Collections.shuffle(list);
        return list;
    }


    public static boolean roll(int o) {
        return random.nextInt(o) == 0;
    }

    public static int max(int o) {
        return random.nextInt(o);
    }

    public static int range(int start, int end) {
        return random.nextInt(end - start) + start;
    }

}
