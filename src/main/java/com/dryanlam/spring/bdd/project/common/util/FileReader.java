package com.dryanlam.spring.bdd.project.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class FileReader {

    @Autowired
    private Path path;

    public <T> List<T> readObjectsFromCsvFile(String filePath, Class<T> clazz, char separator) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz);
        List<T> entities = null;
        String content = readFileContent(filePath);
        MappingIterator<T> result = mapper.readerFor(clazz)
                                          .with(schema.withColumnSeparator(separator).withSkipFirstDataRow(true))
                                          .readValues(content);
        entities = result.readAll();
        return entities;
    }

    public <T> T readObjectFromJsonFile(String filePath, Class<T> clazz) throws IOException {
        String content = readFileContent(filePath);
        return new ObjectMapper().readValue(content, clazz);
    }

    public <T> T readObjectFromJsonFile(String filePath, TypeReference valueTypeRef) throws IOException {
        String content = readFileContent(filePath);
        return new ObjectMapper().readValue(content, valueTypeRef);
    }

    private String readFileContent(String filePath) throws IOException {
        File initialFile = new File(path.buildFilePath(filePath));
        return IOUtils.toString(FileUtils.openInputStream(initialFile));
    }
}
