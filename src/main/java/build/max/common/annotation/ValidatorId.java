package build.max.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.constraints.Length;

/**
 * 验证请求参数id的格式.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
@Length(min = 32, max = 32)
@Documented
@Inherited
public @interface ValidatorId {
}