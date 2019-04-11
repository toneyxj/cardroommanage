package com.xj.myannotation;

import com.google.auto.service.AutoService;
import com.sun.xml.internal.stream.buffer.AbstractProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface TestAnn {

}
