package com.quanxiaoha.vector.store.reader;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/9 16:54
 * @Version: v1.0.0
 * @Description: Txt 文件读取
 */
@Component
public class MyTextReader {
    @Value("classpath:/document/manual.txt")
    private Resource resource;

    /**
     * 读取 Txt
     * @return
     */

    public List<Document> loadText() {
        // 创建 TextReader 对象，用于读取指定资源 (resource) 的文本内容
        TextReader textReader = new TextReader(resource);
        // 添加自定义元数据，如文件名称
        textReader.getCustomMetadata()
                .put("filename", "manual.txt");
        // 读取并转换为 Document 文档集合
        return textReader.read();
    }


    /**
     * 读取 Txt 文档并分块拆分
     * @return
     */
    public List<Document> loadTextAndSplit() {
        // 创建 TextReader 对象，用于读取指定资源 (resource) 的文本内容
        TextReader textReader = new TextReader(resource);

        // 将资源内容解析为 Document 对象集合
        List<Document> documents = textReader.get();

        // 使用 TokenTextSplitter 对文档列表进行分块处理
        List<Document> splitDocuments = new TokenTextSplitter().apply(documents);

        // 返回拆分后的文档分块集合
        return splitDocuments;
    }
}
