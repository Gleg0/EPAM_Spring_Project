package com.epam.conference.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class ServiceUtils<T> {
    static <T> Page<T> toPage(List<T> list, Pageable pageable){
        if(pageable.getPageNumber()*pageable.getPageSize() > list.size()){
            pageable = pageable.withPage(0);
        }
        int start = (int)pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        if (start > end){
            return new PageImpl<>(Collections.emptyList(), pageable, list.size());
        }
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
