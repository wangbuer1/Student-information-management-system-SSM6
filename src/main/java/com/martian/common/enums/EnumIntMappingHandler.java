package com.martian.common.enums;

import com.martian.common.util.EnumUtils;
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
 * 枚举映射处理器 -- 纯枚举映射 映射的是枚举的ordinal值
 */
public class EnumIntMappingHandler extends BaseTypeHandler {

    private static Logger logger = LoggerFactory.getLogger(EnumIntMappingHandler.class);

    private String enumClass;
    private Class clazz;

    public EnumIntMappingHandler(Class type) {
        if (type == null)
            throw new IllegalArgumentException("javaType 参数不能为空");
        this.clazz = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ((Enum) parameter).ordinal());
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.ordinalOf(clazz, value);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.ordinalOf(clazz, value);
        }
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.ordinalOf(clazz, value);
        }
    }
}
