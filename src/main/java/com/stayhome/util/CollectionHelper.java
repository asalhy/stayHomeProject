package com.stayhome.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionHelper {

    private CollectionHelper() {
    }

    public static <S, T> List<T> map(List<S> sources, Function<S, T> mapper) {
        List<T> result = new ArrayList<>();

        if (sources != null) {
            result.addAll(sources
                .stream()
                .map(mapper)
                .collect(Collectors.toList())
            );
        }

        return result;
    }
}
