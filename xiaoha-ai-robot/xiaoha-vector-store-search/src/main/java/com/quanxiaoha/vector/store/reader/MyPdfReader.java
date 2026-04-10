package com.quanxiaoha.vector.store.reader;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/10 09:34
 * @Version: v1.0.0
 * @Description:
 */
@Component
public class MyPdfReader {
    public List<Document> getDocsFromPdf(){
        // 新建 PdfDocumentReader 阅读器
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader("classpath:/document/profile_c.pdf", // PDF 文件路径
                PdfDocumentReaderConfig.builder()
                        .withPageTopMargin(0) // 设置页面顶边距为0
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfTopTextLinesToDelete(0) // 设置删除顶部文本行数为0
                                .build())
                        .withPagesPerDocument(1) // 设置每个文档包含1页
                        .build());

        // 读取并转换为 Document 文档集合
        return pdfReader.read();
    }
}
