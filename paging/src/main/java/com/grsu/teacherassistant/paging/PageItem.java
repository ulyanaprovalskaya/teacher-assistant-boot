package com.grsu.teacherassistant.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageItem {

    private PageItemType pageItemType;

    private int index;

    private boolean active;
}
