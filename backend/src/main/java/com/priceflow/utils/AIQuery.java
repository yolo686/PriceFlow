package com.priceflow.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author 33954
 * #Description AIQuery
 * #Date: 2025/3/30 17:03
 */


//TODO:没测试
public class AIQuery {

    private static final String API_KEY = "sk-a7aba1d9c2f2428d81fef9db9277bbad";
    private static final String API_URL = "https://api.deepseek.com/chat/completions";

    public static String query(String query) {
        try {
            // 构造请求体 JSON
            String requestBody = """
                    {
                      "model": "deepseek-chat",
                      "messages": [
                        {"role": "system", "content": "You are a helpful assistant."},
                        {"role": "user", "content": "%s"}
                      ],
                      "stream": false
                    }
                    """.formatted(query);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // 示例解析（建议改成 Jackson/Gson）
                String body = response.body();
                int start = body.indexOf("\"content\":\"") + 10;
                int end = body.indexOf("\"", start);
                if (start > 9 && end > start) {
                    return body.substring(start, end);
                } else {
                    return "解析失败，请查看返回内容：" + body;
                }
            } else {
                return "请求失败，状态码：" + response.statusCode() + "，返回：" + response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "调用失败：" + e.getMessage();
        }
    }
}
