package com.pine.core.dto.jstree;

import lombok.Data;

/**
 * jsTree 返回json数据
 * Created by chenbupang on 2017-12-6 10:01
 */
@Data
public class JsTree {
    private String id;
    private String text;
    private String type;
    private String icon;
    private StateNode state;
    private String isLeaf;
//    private List<JsTree> children;
    private boolean children;
}
