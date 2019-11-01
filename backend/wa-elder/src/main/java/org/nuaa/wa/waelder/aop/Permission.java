package org.nuaa.wa.waelder.aop;

import org.nuaa.wa.waelder.util.constant.PermissionConstant;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {
    String value() default PermissionConstant.PERMISSION_ANON;
}
