package com.pine.core.dto.jstree;

import lombok.Data;

/**
 * jstree 节点状态属性
 * Created by chenbupang on 2017-12-6 10:02
 */
@Data
public class StateNode {
    private boolean disabled;//失效
    private boolean opened;//展开
    private boolean selected;//选中
    private boolean loaded;
}
