package com.quanxiaoha.ai.robot.service;

import com.quanxiaoha.ai.robot.model.vo.chat.FindChatHistoryMessagePageListReqVO;
import com.quanxiaoha.ai.robot.model.vo.chat.FindChatHistoryMessagePageListRspVO;
import com.quanxiaoha.ai.robot.model.vo.chat.NewChatReqVO;
import com.quanxiaoha.ai.robot.model.vo.chat.NewChatRspVO;
import com.quanxiaoha.ai.robot.utils.PageResponse;
import com.quanxiaoha.ai.robot.utils.Response;

/**
 * @Author: Withnoidea
 * @Date: 2026/4/16 15:23
 * @Version: v1.0.0
 * @Description: 对话
 */
public interface ChatService {
    /**
     * 新建对话
     */
    Response<NewChatRspVO> newChat(NewChatReqVO newChatReqVO);

    /**
     * 查询历史消息
     * @param findChatHistoryMessagePageListReqVO
     * @return
     */
    PageResponse<FindChatHistoryMessagePageListRspVO> findChatHistoryMessagePageList(FindChatHistoryMessagePageListReqVO findChatHistoryMessagePageListReqVO);

}
