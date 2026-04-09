# 项目概览

## 项目简介

这是一个基于 Spring AI 框架构建的智能对话平台，包含通用聊天机器人、智能客服（基于私有知识库）在内的两套 AI 应用。项目集成了多种主流 AI 模型，支持多模态交互、工具调用、联网搜索等高级功能。

## 项目结构

项目包含三个主要子模块：

1. **xiaoha-ai-robot-springboot** - Spring Boot 后端服务
   - 负责所有 AI 模型集成和业务逻辑处理
   - 支持多种 AI 模型（DeepSeek、Ollama、智谱AI、OpenAI、阿里百炼）
   - 实现 MCP (Model Context Protocol) 客户端功能
   - 提供 RESTful API 接口

2. **xiaoha-ai-robot-vue3** - Vue3 前端应用
   - 现代化的聊天界面
   - 支持流式响应和 Markdown 渲染
   - 实时聊天记录管理

3. **xiaoha-mcp-server-qq** - MCP 服务器
   - 独立的 MCP 服务实现
   - 提供 QQ 相关工具功能

## 技术栈

### 后端技术栈
- **框架**: Spring Boot 3.4.7
- **Java 版本**: JDK 21
- **Spring AI 版本**: 1.0.0
- **数据库**: Cassandra（聊天记忆存储）
- **AI 模型**: DeepSeek、Ollama、智谱AI、OpenAI、阿里百炼
- **构建工具**: Maven

### 前端技术栈
- **框架**: Vue 3.5.13
- **构建工具**: Vite 6.2.4
- **UI 组件**: Ant Design Vue 4.2.6
- **样式**: Tailwind CSS 4.1.8
- **路由**: Vue Router 4.5.1
- **Markdown**: markdown-it + highlight.js
- **包管理**: npm

### 其他技术
- **MCP (Model Context Protocol)**: 用于工具调用和上下文管理
- **SSE (Server-Sent Events)**: 流式响应支持
- **Docker**: 服务部署（如 SearXNG 搜索引擎）

## 主要功能

### 后端功能
- 多 AI 模型支持（DeepSeek、Ollama、智谱AI、OpenAI、阿里百炼）
- 聊天记忆与持久化（基于 Cassandra）
- MCP 客户端集成（支持 stdio 和 SSE 两种通信模式）
- 多模态支持（文本、图片、音频、视频）
- 工具调用功能
- 结构化输出
- 提示词模板管理
- 联网搜索集成
- RAG 检索增强

### 前端功能
- 实时聊天界面
- 流式响应渲染
- Markdown 格式化显示
- 代码高亮
- 聊天记录管理
- 模型选择
- 响应式设计

## 构建和运行

### 前置要求
- JDK 21+
- Node.js 18+
- Maven 3.8+
- Cassandra 数据库（用于聊天记忆存储）

### 后端服务构建和运行

```bash
# 进入后端项目目录
cd xiaoha-ai-robot/xiaoha-ai-robot-springboot

# 清理并编译
mvn clean compile

# 运行测试
mvn test

# 打包
mvn package

# 运行应用
mvn spring-boot:run

# 或者直接运行打包后的 jar 文件
java -jar target/xiaoha-ai-robot-springboot-0.0.1-SNAPSHOT.jar
```

后端服务默认运行在 `http://localhost:8080`

### 前端应用构建和运行

```bash
# 进入前端项目目录
cd xiaoha-ai-robot/xiaoha-ai-robot-vue3

# 安装依赖（首次运行）
npm install

# 开发模式运行
npm run dev

# 生产环境构建
npm run build

# 预览生产构建
npm run preview
```

前端开发服务器默认运行在 `http://localhost:5173`（或 Vite 分配的其他端口）

### MCP 服务器运行

```bash
# 进入 MCP 服务器项目目录
cd xiaoha-ai-robot/xiaoha-mcp-server-qq

# 构建和运行
mvn spring-boot:run
```

## 配置说明

### 后端配置 (application.yml)

主要配置项位于 `xiaoha-ai-robot-springboot/src/main/resources/application.yml`：

- **服务器端口**: 默认 8080
- **Cassandra 配置**: 用于聊天记忆存储
  - contact-points: 127.0.0.1
  - port: 9042
  - keyspace: xiaoha_ai_robot
  
- **AI 模型配置**:
  - DeepSeek: API Key 和模型参数
  - Ollama: 本地服务地址和模型配置
  - 智谱AI: API Key 和模型参数
  - OpenAI: 支持阿里云百炼兼容模式
  - MCP 客户端: 支持同步/异步模式，超时配置等

### MCP 服务器配置 (mcp-servers-config.json)

配置文件位于 `xiaoha-ai-robot-springboot/src/main/resources/mcp-servers-config.json`：

支持配置多个 MCP 服务器，包括：
- amap-maps: 高德地图服务
- filesystem: 文件系统访问服务

## 开发约定

### 代码规范
- 后端遵循 Spring Boot 最佳实践
- 使用 Lombok 减少样板代码
- 前端遵循 Vue 3 Composition API 风格
- 使用 TypeScript 类型检查（如果启用）

### 目录结构
- 后端遵循标准 Maven 项目结构
- 前端遵循 Vite 推荐的项目结构
- 配置文件统一放在 `src/main/resources` 或相应位置

### API 设计
- RESTful 风格的 API 设计
- 统一的响应格式
- 支持 SSE 流式响应

### 测试
- 后端使用 Spring Boot Test
- 前端测试框架待完善
- 建议为新增功能编写单元测试

## 常用命令

### Maven 命令（后端）
```bash
mvn clean                # 清理构建产物
mvn compile              # 编译源代码
mvn test                 # 运行测试
mvn package              # 打包应用
mvn install              # 安装到本地仓库
mvn dependency:tree      # 查看依赖树
```

### npm 命令（前端）
```bash
npm install              # 安装依赖
npm run dev              # 开发模式
npm run build            # 生产构建
npm run preview          # 预览构建
npm run lint             # 代码检查（如果配置）
```

### Git 命令
```bash
git status               # 查看状态
git add .                # 添加所有更改
git commit -m "message"  # 提交更改
git push                 # 推送到远程
git pull                 # 拉取更新
```

## 注意事项

1. **API Key 安全**: 生产环境应使用环境变量或配置中心管理 API Key，不要将敏感信息提交到代码仓库
2. **Cassandra 数据库**: 确保已正确安装和配置 Cassandra 数据库
3. **Ollama 服务**: 如需使用 Ollama 模型，需先安装并启动 Ollama 服务
4. **MCP 服务器**: MCP 服务器需要独立运行，确保端口配置正确
5. **端口冲突**: 如遇到端口冲突，可在配置文件中修改相应端口

## 故障排查

### 后端启动失败
- 检查 JDK 版本是否为 21+
- 检查 Cassandra 连接配置
- 查看日志输出，定位具体错误

### 前端构建失败
- 确保已安装 Node.js 18+
- 删除 node_modules 重新安装依赖
- 检查网络连接，确保依赖下载正常

### AI 模型调用失败
- 检查 API Key 是否正确
- 确认网络连接正常
- 查看后端日志获取详细错误信息

## 扩展和定制

### 添加新的 AI 模型
1. 在 `pom.xml` 中添加对应的 Spring AI 依赖
2. 在 `application.yml` 中配置模型参数
3. 创建对应的 Controller 类

### 添加新的 MCP 工具
1. 在 `mcp-servers-config.json` 中配置新的 MCP 服务器
2. 在后端代码中实现对应的工具调用逻辑

### 自定义前端界面
1. 修改 `xiaoha-ai-robot-vue3/src/views/Index.vue`
2. 添加新的组件到 `xiaoha-ai-robot-vue3/src/components`
3. 使用 Tailwind CSS 进行样式定制

## 参考资源

- [Spring AI 官方文档](https://docs.spring.io/spring-ai/reference/)
- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Vue 3 官方文档](https://vuejs.org/)
- [Ant Design Vue 文档](https://antdv.com/)
- [MCP 协议规范](https://modelcontextprotocol.io/)