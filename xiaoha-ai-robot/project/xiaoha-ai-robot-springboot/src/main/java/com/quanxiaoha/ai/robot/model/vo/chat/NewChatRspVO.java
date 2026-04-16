package com.quanxiaoha.ai.robot.model.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/16 15:19
 * @Version: v1.0.0
 * @Description: 新建对话
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewChatRspVO {
    /**
     * 摘要
     */

    private String summary;

    /**
     *对话UUID
     */
    private String uuid;
}
