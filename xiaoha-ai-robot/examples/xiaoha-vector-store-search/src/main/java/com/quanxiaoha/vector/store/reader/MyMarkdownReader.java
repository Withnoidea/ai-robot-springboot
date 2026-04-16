package com.quanxiaoha.vector.store.reader;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 08:56
 * @Version: v1.0.0
 * @Description:
 */
@Component
public class MyMarkdownReader {
    @Value("classpath:/document/code.md")
    private Resource resource;

    public List<Document> loadMarkdown(){
        //MarkdownDocumentReader 阅读器配置类
        MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                .withHorizontalRuleCreateDocument(true) //遇到水平线---，则创建新文档
                .withIncludeCodeBlock( true) //排除代码块 （代码块生成单独文档）
                .withIncludeBlockquote( true) //排除块引用 （块引用生成单独文档）
                .withAdditionalMetadata("filename", "code.md")
                .build();

        // 新建 MarkdownDocumentReader 阅读器
        MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);

        // 读取并转换为 Document 文档集合
        return reader.get();
    }
}
