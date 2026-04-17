package com.quanxiaoha.ai.robot.controller;

import com.google.common.collect.Lists;
import com.quanxiaoha.ai.robot.advisor.CustomChatMemoryAdvisor;
import com.quanxiaoha.ai.robot.advisor.CustomStreamLoggerAndMessage2DBAdvisor;
import com.quanxiaoha.ai.robot.advisor.NetworkSearchAdvisor;
import com.quanxiaoha.ai.robot.aspect.ApiOperationLog;
import com.quanxiaoha.ai.robot.domain.mapper.ChatMessageMapper;
import com.quanxiaoha.ai.robot.model.vo.chat.*;
import com.quanxiaoha.ai.robot.model.vo.chat.FindChatHistoryMessagePageListRspVO;
import com.quanxiaoha.ai.robot.service.ChatService;
import com.quanxiaoha.ai.robot.service.SearXNGService;
import com.quanxiaoha.ai.robot.service.SearchResultContentFetcherService;
import com.quanxiaoha.ai.robot.utils.PageResponse;
import com.quanxiaoha.ai.robot.utils.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;


@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    @Resource
    private ChatService chatService;
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Resource
    private ChatMessageMapper chatMessageMapper;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private SearXNGService searXNGService;
    @Resource
    private SearchResultContentFetcherService searchResultContentFetcherService;


    @PostMapping("/new")
    @ApiOperationLog(description = "新建对话")
    public Response<?> newChat(@RequestBody @Validated NewChatReqVO newChatReqVO) {
        return chatService.newChat(newChatReqVO);
    }

    /**
     * 流式对话
     * @return
     */
    @PostMapping(value = "/completion", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiOperationLog(description = "流式对话")
    public Flux<AIResponse> chat(@RequestBody @Validated AiChatReqVO aiChatReqVO) {
        // 用户消息
        String userMessage = aiChatReqVO.getMessage();
        // 模型名称
        String modelName = aiChatReqVO.getModelName();
        // 温度值
        Double temperature = aiChatReqVO.getTemperature();

        // 是否开启联网搜索
        boolean networkSearch = aiChatReqVO.getNetworkSearch();


        // 构建 ChatModel
        ChatModel chatModel = OpenAiChatModel.builder()
                .openAiApi(OpenAiApi.builder()
                        .baseUrl(baseUrl)
                        .apiKey(apiKey)
                        .build())
                .build();

        // 动态设置调用的模型名称、温度值
        ChatClient.ChatClientRequestSpec chatClientRequestSpec = ChatClient.create(chatModel)
                .prompt()
                .options(OpenAiChatOptions.builder()
                        .model(modelName)
                        .temperature(temperature)
                        .build())
                .user(userMessage); // 用户提示词

        // Advisor 集合
        List<Advisor> advisors = Lists.newArrayList();

        // 是否开启了联网搜索
        if (networkSearch) {
            advisors.add(new NetworkSearchAdvisor(searXNGService, searchResultContentFetcherService));
        } else {
            // 添加自定义对话记忆 Advisor（以最新的 50 条消息作为记忆）
            advisors.add(new CustomChatMemoryAdvisor(chatMessageMapper, aiChatReqVO, 50));
        }
        // 添加自定义打印流式对话日志 Advisor
        advisors.add(new CustomStreamLoggerAndMessage2DBAdvisor(chatMessageMapper, aiChatReqVO, transactionTemplate));

        // 应用 Advisor 集合
        chatClientRequestSpec.advisors(advisors);


        // 流式输出
        return chatClientRequestSpec
                .stream()
                .content()
                .mapNotNull(text -> AIResponse.builder().v(text).build()); // 构建返参 AIResponse

    }

    @PostMapping("/message/list")
    @ApiOperationLog(description = "查询对话历史消息")
    public PageResponse<FindChatHistoryMessagePageListRspVO> findChatMessagePageList(@RequestBody @Validated FindChatHistoryMessagePageListReqVO findChatHistoryMessagePageListReqVO) {
        return chatService.findChatHistoryMessagePageList(findChatHistoryMessagePageListReqVO);
    }

}
