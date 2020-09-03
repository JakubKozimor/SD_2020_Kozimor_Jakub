package com.learning.pageable;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PageHelper {

    public static Object preparePageFromList(List<?> list, Pageable pageable) {
        final int start = pageable.getPageNumber() * pageable.getPageSize();
        final int end = Math.min(start + pageable.getPageSize(), list.size());
        try {
            return new PageImpl<>(list.subList(start, end), pageable, list.size());
        } catch (IllegalArgumentException err) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
    }

    public static Object preparePageFromSet(Set<?> list, Pageable pageable) {
        ArrayList<?> arrayList = new ArrayList<>(list);
        return preparePageFromList(arrayList, pageable);
    }
}
