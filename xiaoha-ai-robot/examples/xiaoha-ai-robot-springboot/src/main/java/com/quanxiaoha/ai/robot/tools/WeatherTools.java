package com.quanxiaoha.ai.robot.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 10:12
 * @Version: v1.0.0
 * @Description:
 */

@Slf4j
public class WeatherTools {
    @Tool(description = "获取当日的天气预报，时间参数需为 ISO-86601 格式")
    String getWeather(String time){
        log.info("## time : {}", time);
        //TODO 调用第三方接口，获取当日天气情况
        return "今天天气晴朗，最低18℃，最高温度 38℃";
    }

}
