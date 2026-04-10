package com.quanxiaoha.vector.store.controller;

import com.quanxiaoha.vector.store.reader.*;
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

    @Resource
    private MyJsonReader jsonReader;

    @Resource
    private MyMarkdownReader markdownReader;

    @Resource
    private MyHtmlReader htmlReader;

    @Resource
    private MyPdfReader pdfReader;

    @GetMapping(value = "/txt")
    public List<Document> readText() {
        return textReader.loadText();
    }



    @GetMapping(value = "/txt2")
    public List<Document> readText2() {
        return textReader.loadTextAndSplit();
    }

    @GetMapping(value = "/json")
    public List<Document> readJson() {
        return jsonReader.loadJson();
    }

    @GetMapping(value = "/md")
    public List<Document> readMarkdown() {
        return markdownReader.loadMarkdown();
    }

    @GetMapping(value = "/html")
    public List<Document> readHtml() {
        return htmlReader.loadHtml();
    }

    @GetMapping(value = "/pdf")
    public List<Document> readPdf() {
        return pdfReader.getDocsFromPdf();
    }
}
