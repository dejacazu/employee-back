package com.employee.amaris.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;

public class CookieInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Ejecuta la solicitud inicial
        ClientHttpResponse response = execution.execute(request, body);

        // Extrae las cookies de la respuesta
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            StringBuilder cookieBuilder = new StringBuilder();
            for (String cookie : cookies) {
                cookieBuilder.append(cookie.split(";")[0]).append("; "); // Agrega solo la primera parte de cada cookie
            }
            String cookieHeader = cookieBuilder.toString().trim();
            if (cookieHeader.endsWith(";")) {
                cookieHeader = cookieHeader.substring(0, cookieHeader.length() - 1);
            }

            // Agrega la cookie a la siguiente solicitud
            request.getHeaders().add("Cookie", cookieHeader);
            request.getHeaders().add("User-Agent", "Mozilla/5.0 ...");
            request.getHeaders().add("Accept", "text/html,application/xhtml+xml,...");
            request.getHeaders().add("Accept-Language", "en;q=0.8");
            request.getHeaders().add("Cache-Control", "max-age=0");
            request.getHeaders().add("Connection", "keep-alive");
            request.getHeaders().add("Sec-Gpc", "1");
            request.getHeaders().add("Upgrade-Insecure-Requests", "1");
            request.getHeaders().add("Accept-Encoding", "gzip, deflate");
            request.getHeaders().add("DNT", "1");
            request.getHeaders().add("Referer", "http://dummy.restapiexample.com/");

            // Vuelve a ejecutar la solicitud con la cookie y el User-Agent
            response = execution.execute(request, body);
        }
        return response;
    }
}
