package com.quanxiaoha.ai.robot.controller;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: withnoidea
 * @Date: 2026/01/22
 * @Version: v1.0.0
 * @Description: 文生图
 **/
@RestController
@RequestMapping("/v10/ai")
@Slf4j
public class Text2ImgController {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    /**
     * 调用阿里百炼图生文大模型
     * @param prompt 提示词
     * @return
     */
    @GetMapping("/text2img")
    public String text2Image(@RequestParam(value = "prompt") String prompt) {
        // 构建文生图参数
        ImageSynthesisParam param = ImageSynthesisParam.builder()
                .apiKey(apiKey) // 阿里百炼 API Key
                .model("wanx2.1-t2i-plus") // 模型名称
                .prompt(prompt) // 提示词
                .n(1) // 生成图片的数量，这里指定为一张
                .size("1024*1024") // 输出图像的分辨率
                .build();

        // 同步调用 AI 大模型，生成图片
        ImageSynthesis imageSynthesis = new ImageSynthesis();
        ImageSynthesisResult result = null;
        try {
            log.info("## 同步调用，请稍等一会...");
            result = imageSynthesis.call(param);
        } catch (ApiException | NoApiKeyException e){
            log.error("", e);
        }

        // 返回生成的结果（包含图片的 URL 链接）
        return JsonUtils.toJson(result);
    }

}

