package cn.allcheer.springbootbylihui.utils.baofu.http;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * 项目名称：baofu-fopay-sdk-java
 * 类名称：表单参数
 * 类描述：
 * @version
 */
@XmlType(name = "httpMethod")
@XmlEnum
public enum HttpMethod {
	GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS;

	/**
	 * @return
	 */
	public String value() {
		return name();
	}

	/**
	 * @param v
	 * @return
	 */
	public static HttpMethod fromValue(String v) {
		return valueOf(v);
	}

	/**
	 * @return
	 */
	public static HttpMethod getDefault() {
		return POST;
	}
}
