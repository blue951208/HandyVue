package org.handy.handyvue.service.chat;

import io.micrometer.common.lang.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
public class RagChatService {

    private final ChatClient chatClient;

    public RagChatService(ChatClient.Builder chatClientBuilder, Advisor[] advisors) {
        this.chatClient = chatClientBuilder.defaultOptions(ChatOptions.builder().temperature(0.0).build())
                .defaultAdvisors(advisors).build();
    }

    public Flux<String> stream(Prompt prompt, String conversationId,Optional<String> filterExpressionAsOpt) {
        // 응답을 받아오는 코드 추가
        return prepareRequest(prompt, conversationId, filterExpressionAsOpt)
                .stream()
                .content();
    }

    public @Nullable ChatResponse call(Prompt prompt, String conversationId, Optional<String> filterExpressionAsOpt){
        return prepareRequest(prompt, conversationId, filterExpressionAsOpt)
                .call()
                .chatResponse();
    }

    private ChatClient.ChatClientRequestSpec prepareRequest(Prompt prompt,
                                                            String conversationId,
                                                            Optional<String> filterExpressionAsOpt){
        return chatClient.prompt(prompt)
                .advisors(advisorSpec ->
                        advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .advisors(advisorSpec ->
                        advisorSpec.param(VectorStoreDocumentRetriever.FILTER_EXPRESSION, filterExpressionAsOpt.orElse("")
                        ));
    }


}
