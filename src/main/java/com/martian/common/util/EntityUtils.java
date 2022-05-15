package com.martian.common.util;

import com.martian.entity.BaseEntity;
import com.martian.enums.YNEnum;

import java.util.Date;

/**
 * 实体操作工具类
 */
public class EntityUtils {

    public static void init(BaseEntity entity) {
        init(null, entity);
    }

    public static void init(String userId, BaseEntity entity) {
        Date now = new Date();
        userId = CommonUtils.isEmpty(userId) ? "admin" : userId;
        if (CommonUtils.isEmpty(entity.getId())) {
            entity.setIsValid(YNEnum.YES);
            entity.setCreateBy(userId);
            entity.setCreateTime(now);
        }
        entity.setUpdateBy(userId);
        entity.setUpdateTime(now);
    }

}
