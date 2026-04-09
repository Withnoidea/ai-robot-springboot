package com.quanxiaoha.ai.robot.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * @Author: withnoidea
 * @Date: 2026/4/8 14:51
 * @Version: v1.0.0
 * @Description: 日期 Tool
 **/
@Slf4j
public class DateTimeTools {
    @Tool(description = "获取当前时间")
    String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }
}
