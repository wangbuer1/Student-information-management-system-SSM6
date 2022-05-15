package com.martian.common.converter;

import com.martian.common.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 枚举转换
 */
public class StringToEnumConverter implements ConverterFactory<String, IEnum> {
    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(targetType);
    }

    private class StringToEnum<T extends IEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String code) {
            if (code == null || code.length() == 0) {
                return null;
            }
            for (IEnum e : enumType.getEnumConstants()) {
                if (e.getCode().equals(code)) {
                    return (T) e;
                }
            }
            throw new IllegalArgumentException("未知的枚举类型：" + code + ",请检查" + enumType.getSimpleName());
        }
    }
}
