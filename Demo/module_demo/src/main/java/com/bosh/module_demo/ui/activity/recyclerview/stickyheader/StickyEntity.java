package com.bosh.module_demo.ui.activity.recyclerview.stickyheader;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author lzq
 * @date 2019-08-14
 */
public class StickyEntity extends SectionEntity<DataEntity> {

    public StickyEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public StickyEntity(DataEntity data) {
        super(data);
    }
}
