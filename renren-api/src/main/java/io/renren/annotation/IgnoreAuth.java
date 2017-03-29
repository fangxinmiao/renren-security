package io.renren.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author chenshun
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
