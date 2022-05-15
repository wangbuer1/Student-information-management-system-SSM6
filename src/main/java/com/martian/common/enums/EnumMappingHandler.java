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
 * 枚举映射处理器 -- 纯枚举映射
 */
public class EnumMappingHandler extends BaseTypeHandler {

    private static Logger logger = LoggerFactory.getLogger(EnumMappingHandler.class);

    private String enumClass;
    private Class clazz;

    public EnumMappingHandler(Class type) {
        if (type == null)
            throw new IllegalArgumentException("javaType 参数不能为空");
        this.clazz = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, ((Enum) parameter).name());
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.nameOf(clazz, value);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.nameOf(clazz, value);
        }
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return CommonUtils.isEmpty(value) ? null : EnumUtils.nameOf(clazz, value);
        }
    }
}
