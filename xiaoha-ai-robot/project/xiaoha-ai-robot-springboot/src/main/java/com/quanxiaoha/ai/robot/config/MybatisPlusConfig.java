package com.quanxiaoha.ai.robot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/15 13:37
 * @Version: v1.0.0
 * @Description: TODO
 */
@Configuration
@MapperScan("com.quanxiaoha.ai.robot.domain.mapper")
public class MybatisPlusConfig {
}
