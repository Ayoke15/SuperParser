package org.example.st.recomendation.unit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonFileUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveToFile(String filePath, Object data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    public <T> T readFromFile(String filePath, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }
}
