package com.pine.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenbupang on 2017-11-23 16:40
 */
public interface PineConst {
    String CLASSPATH = new File(PineConst.class.getResource("/").getPath()).getPath() + File.separator;
    /**
     * 存放用户登录成功后的session信息
     */
    String USER_LOGIN_SESSION = "userLoginSession";

    String BUSSINESS_PRIMARY_ID = "bs_primary_id";
    String BUSSINESS_PRIMARY_NAME = "bs_primary_name";
    String BUSSINESS_ENTITY = "bs_entity";

    String USER_IN_COOKIE = "U_C_ID";

    String DEFAULT_PWD = "1234";
    String AES_SALT = "0123456789abcdef";

    /**
     * 启用
     */
    String STATUS_IS_ONUSE = "1";
    /**
     * 停用
     */
    String STATUS_IS_UNUSE = "0";
    /**
     * 删除
     */
    String STATUS_IS_DELETED = "-1";
    /**
     * 正序/升序
     */
    boolean DIRECTION_ASC = true;
    /**
     * 倒序/降序
     */
    boolean DIRECTION_DESC = false;

    /**
     * 允许上传的最大文件:20M
     */
    int MAX_FILE_SIZE = 204800;

    /**
     * 系统配置参数
     */
    Config OPTIONS = Config.of(new HashMap<>());

    /**
     * 拦截ip
     */
    List<String> BLOCK_IPS = new ArrayList<>();
    /**
     * dataTable 默认显示条数
     */
    int TABLE_PAGE_SIZE = 8;
    /**
     * dataTable 默认起始位置
     */
    int TABLE_PAGE = 0;

}
