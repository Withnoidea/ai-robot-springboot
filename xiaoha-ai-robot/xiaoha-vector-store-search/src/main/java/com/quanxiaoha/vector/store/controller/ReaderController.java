package com.quanxiaoha.vector.store.controller;

import com.quanxiaoha.vector.store.reader.MyJsonReader;
import com.quanxiaoha.vector.store.reader.MyTextReader;
import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 16:58
 * @Version: v1.0.0
 * @Description: 文档读取
 */
@RestController
@RequestMapping("/read")
public class ReaderController {

    @Resource
    private MyTextReader textReader;

    @GetMapping(value = "/txt")
    public List<Document> readText() {
        return textReader.loadText();
    }

    @Resource
    private MyJsonReader jsonReader;

    @GetMapping(value = "/txt2")
    public List<Document> readText2() {
        return textReader.loadTextAndSplit();
    }

    @GetMapping(value = "/json")
    public List<Document> readJson() {
        return jsonReader.loadJson();
    }

}
