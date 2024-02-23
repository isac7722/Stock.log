package com.code2am.stocklog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class ConvertUtil {

    public static Object ObjectToJsonObject(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        String convertToJson;
        Object converObj;

        try {
            convertToJson = mapper.writeValueAsString(obj);
            converObj = parser.parse(convertToJson);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return converObj;
    }
}
