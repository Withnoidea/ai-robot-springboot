package com.quanxiaoha.ai.robot.aspect;
import java.lang.annotation.*;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/16 14:11
 * @Version: v1.0.0
 * @Description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     *
     * @return
     */
    String description() default "";
}
