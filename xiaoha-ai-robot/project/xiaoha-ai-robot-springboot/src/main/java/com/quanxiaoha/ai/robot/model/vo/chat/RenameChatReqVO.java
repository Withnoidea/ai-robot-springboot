package com.quanxiaoha.ai.robot.model.vo.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/17 13:25
 * @Version: v1.0.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RenameChatReqVO {

    @NotNull(message = "对话 ID 不能为空")
    private long Id;

    @NotBlank(message = "对话摘要不能为空")
    private String summary;
}
