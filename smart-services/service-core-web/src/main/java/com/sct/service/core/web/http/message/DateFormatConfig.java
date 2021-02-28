package com.sct.service.core.web.http.message;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sct.commons.file.location.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonComponent
public class DateFormatConfig {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String[] patterns = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
    private static List<SimpleDateFormat> dateFormats = new ArrayList<>();

    static {
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
        dateFormats.add(new SimpleDateFormat("yyyy/MM/dd"));
    }

    private static Date parseDate(String text) throws ParseException {
        for (SimpleDateFormat simpleDateFormat : dateFormats) {
            try {
                return simpleDateFormat.parse(text);
            } catch (Exception e) {
            }
        }
        throw new ParseException("Unable to parse the date: " + text, -1);
    }

    /**
     * 日期格式化
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (date == null) {
                jsonGenerator.writeString("");
            } else {
                jsonGenerator.writeString(dateFormat.format(date));
            }
        }
    }

    /**
     * 解析日期字符串
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            try {
                String text = jsonParser.getText();
                if (StringUtils.isEmpty(text)) {
                    return null;
                } else {
                    return parseDate(text);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
