package io.github.ooknight.universe.support.utils.component;

import io.ebean.PagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public final class PageUtils {

    public <T> Page<T> of(PagedList<T> data, Pageable pageable) {
        return new PageImpl<>(data.getList(), pageable, data.getTotalCount());
    }
}
