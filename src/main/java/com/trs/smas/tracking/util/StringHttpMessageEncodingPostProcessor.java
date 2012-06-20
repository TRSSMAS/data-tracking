/**
 * Title:		TRS SMAS
 * Copyright:	Copyright(c) 2011,TRS. All rights reserved.
 * Company:		北京拓尔思信息技术股份有限公司(www.trs.com.cn)
 */
package com.trs.smas.tracking.util;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 修改StringHttpMessageConverter的编码为UTF－8
 *
 * @author 北京拓尔思信息技术股份有限公司
 * @since huangshengbo @ Oct 12, 2011
 */
public class StringHttpMessageEncodingPostProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(Object bean, String name)
			throws BeansException {
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessBeforeInitialization(Object bean, String name)
			throws BeansException {
		 if (bean instanceof AnnotationMethodHandlerAdapter) {
	            HttpMessageConverter<?>[] convs = ((AnnotationMethodHandlerAdapter) bean).getMessageConverters();
	            for (HttpMessageConverter<?> conv: convs) {
	                if (conv instanceof StringHttpMessageConverter) {
	                    ((StringHttpMessageConverter) conv).setSupportedMediaTypes(
	                        Arrays.asList(new MediaType("text", "plain", 
	                            Charset.forName("UTF-8"))));
	                }
	            }
	        }
	     return bean;
	}

}
