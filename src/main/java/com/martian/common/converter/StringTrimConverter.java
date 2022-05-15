package com.martian.common.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 去掉字符串两边空格，如果去除后为空设置为null
 */
public class StringTrimConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		try {
			if (source != null) {
				source = source.trim();
				if (source.equals("")) {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source;
	}
}
