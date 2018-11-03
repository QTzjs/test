package com.happy.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface LogAnnotation {  
	//功能名称
    String operateModelNm();  
    //方法名称
    String operateFuncNm();  
   
}  