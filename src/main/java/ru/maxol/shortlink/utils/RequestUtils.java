package ru.maxol.shortlink.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static Map<String, String> getBodyRequest(HttpServletRequest request) {
        Map<String, String> jsonRequest = new HashMap<>();
        try {
            byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
            jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }
}
