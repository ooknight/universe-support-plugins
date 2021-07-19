package io.github.ooknight.universe.support.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Collection;

public final class CollectionUtils {

    public <T> boolean contains(Collection<T> collection, T value) {
        return CollectionUtil.contains(collection, value);
    }

    public boolean empty(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }
}
