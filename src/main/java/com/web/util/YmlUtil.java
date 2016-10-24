package com.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * YML文件转化工具类
 *
 * @author 田军兴
 * @date 2016-10-22
 */
public class YmlUtil {

	/**
	 * 解析yml文件为JSON字符串
	 * 
	 * @param url
	 *            yml文件路径
	 */
	public static Map getYmlString(String url) {
		Yaml yml = new Yaml();
		if (StringUtil.isNotEmpty(url)) {
			File file = new File(url);
			Map map = null;
			try {
				map = (Map) yml.load(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return map;
		}
		return null;
	}
}
