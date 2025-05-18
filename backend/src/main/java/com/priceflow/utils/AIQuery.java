package com.priceflow.utils;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.coze.openapi.client.chat.*;
import com.coze.openapi.client.chat.model.*;
import com.coze.openapi.client.connversations.message.model.Message;
import com.coze.openapi.service.auth.TokenAuth;
import com.coze.openapi.service.service.CozeAPI;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 33954
 * #Description AIQuery
 * #Date: 2025/3/30 17:03
 */

/*
This example describes how to use the chat interface to initiate conversations,
poll the status of the conversation, and obtain the messages after the conversation is completed.
* */
public class AIQuery {

    // For non-streaming chat API, it is necessary to create a chat first and then poll the chat
    // results.
    public static CompletableFuture<String> queryAsync(String content) {
        String token = "pat_6eFUEV3jl8Bj4fCEoJuwxfuyx9GRtDXS076Bwjh83L4SYZSQzHyo1sYK04bc9rQC";
        String botID = "7505436901021745187";
        String uid = "33954";
        String baseURL = "https://api.coze.cn/v3/chat/";

        TokenAuth authCli = new TokenAuth(token);
        StringBuilder rsp = new StringBuilder();
        CompletableFuture<String> future = new CompletableFuture<>();

        CozeAPI coze = new CozeAPI.Builder()
                .baseURL(baseURL)
                .auth(authCli)
                .readTimeout(10000)
                .build();

        CreateChatReq req = CreateChatReq.builder()
                .botID(botID)
                .userID(uid)
                .messages(Collections.singletonList(Message.buildUserQuestionText(content)))
                .build();

        Flowable<ChatEvent> resp = coze.chat().stream(req);
        resp.subscribeOn(Schedulers.io())
                .subscribe(
                        event -> {
                            if (ChatEventType.CONVERSATION_MESSAGE_DELTA.equals(event.getEvent())) {
                                rsp.append(event.getMessage().getContent());
                            }
                            if (ChatEventType.CONVERSATION_CHAT_COMPLETED.equals(event.getEvent())) {
                                System.out.println("Token usage:" + event.getChat().getUsage().getTokenCount());
                                future.complete(rsp.toString());
                            }
                        },
                        throwable -> {
                            System.err.println("Error occurred: " + throwable.getMessage());
                            future.completeExceptionally(throwable);
                        },
                        () -> {
                            System.out.println("Stream completed");
                            if (!future.isDone()) {
                                future.complete(rsp.toString());
                            }
                            coze.shutdownExecutor();
                        });

        return future;
    }
}
