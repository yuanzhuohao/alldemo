package com.example.jessyuan.alldemo.helper;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.Policy;

import javax.inject.Scope;

/**
 * Created by JessYuan on 13/12/2016.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
