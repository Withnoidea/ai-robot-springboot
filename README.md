# ai-robot-springboot


## AI 聊天机器人、智能客服

- **项目介绍**：一款基于 Spring AI 框架构建的智能对话平台，包含通用聊天机器人、智能客服（基于私有知识库）在内的两套 AI 应用。
- 技术栈：
  - **后端**：Spring Boot 3.x、JDK 21、Spring AI、DeepSeek、MyBatis Plus、PostgreSQL、RAG、SSE、SearXNG、OkHttp3、Jsoup、Docker
  - **前端**：Vue 3.x、Vite 4、Ant Design Vue、Tailwind CSS、Pinia、Axios、EventSource / fetch-event-source
- **担任角色**： 全栈开发
- 项目职责：
  - **聊天记忆与持久化**：集成 PostgreSQL 作为核心业务与向量存储数据库，实现了会话的长上下文记忆管理。自定义 Advisor 组件，在每次模型调用前后自动注入历史对话，消息持久化，并完整收集流式与非流式响应，为后续分析与审计提供了可靠的数据基础。
  - **联网实时搜索**：负责 Docker 自部署 SearXNG 聚合搜索引擎，后端利用 OkHttp3 并发请求，结合 CompletableFuture 与自定义线程池优化搜索速度，并使用 Jsoup 清洗网页正文，有效将外部信息 Token 消耗降低超过 60%，使 AI 大模型具备实时、准确的信息获取与回答能力。
  - **RAG 检索增强**：负责自定义 RAG Advisor 组件，检索私有知识库的向量数据，并动态构建增强提示词模板，成功交付了 “按指定问答语料进行对话” 的智能客服。
  - **提示词工程**：负责多个场景的提示词编写与优化工作，包含联网搜索、客服问答场景。通过角色设定、规则约束与上下文管理策略，显著提升了大模型在复杂任务中的准确性、可靠性与输出一致性。
  - **后端业务开发**：基于 Spring Boot 3.x 搭建了完整的后端服务体系。涵盖对话管理、消息分页、知识库 Markdown 文件管理。运用 Spring Event 发布/订阅模式，优雅解耦了文件上传后的解析、向量化等异步处理流程，提升了系统响应速度与代码的清晰度。
  - **前端应用开发**：使用 Vue 3 与 Vite 构建单页应用，基于 Ant Design Vue 与 Tailwind CSS 打造了风格统一的聊天界面与管理页；采用 SSE 协议对接后端流式接口，并封装了可实时渲染流式 Markdown、聊天对话框、侧边栏等一系列自定义组件；使用 Pinia 管理全局模型选择、搜索开关等状态；实现了聊天记录与对话列表的滚动分页加载等。

