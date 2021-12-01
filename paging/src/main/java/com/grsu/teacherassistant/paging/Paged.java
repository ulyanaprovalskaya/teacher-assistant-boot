package com.grsu.teacherassistant.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Getter
@Setter
@AllArgsConstructor
public class Paged<T> {

    private Page<T> page;

    private Paging paging;

    public long getTotal(){
        return page.getTotalPages();
    }
}
