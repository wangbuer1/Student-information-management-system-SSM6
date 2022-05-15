package com.martian.common.enums;

import com.martian.common.util.CommonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举映射处理器 -- 枚举中的code值映射 兼容老逻辑取枚举code来映射的枚举
 */
public class EnumCodeMappingHandler extends BaseTypeHandler<IEnum> {

    private static Logger logger = LoggerFactory.getLogger(EnumCodeMappingHandler.class);

    private Class<IEnum> clazz;
    private final IEnum[] enums;

    public EnumCodeMappingHandler(Class<IEnum> type) {
        if (type == null)
            throw new IllegalArgumentException("javaType 参数不能为空");
        this.clazz = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + "枚举类内没有值");
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnum iEnum, JdbcType jdbcType) throws SQLException {
        ps.setString(i, iEnum.getCode());
    }

    @Override
    public IEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateIEnum(code);
        }
    }

    @Override
    public IEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateIEnum(code);
        }
    }

    @Override
    public IEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateIEnum(code);
        }
    }

    /**
     * 枚举类型转换，构造函数提取了枚举的元素数组enums，提高遍历效率
     *
     * @param code 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private IEnum locateIEnum(String code) {
        if (CommonUtils.isEmpty(code)) return null;
        for (IEnum e : enums) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + code + ",请检查" + clazz.getSimpleName());
    }
}
