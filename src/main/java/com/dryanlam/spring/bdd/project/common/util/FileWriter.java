package com.dryanlam.spring.bdd.project.common.util;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class FileWriter {

    @Autowired
    private Path path;

    public void writeObjectsToJsonFile(String filePath, Object object) throws IOException {
        filePath = path.buildFilePath(filePath);
        ObjectMapper mapper = new ObjectMapper();
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        ObjectWriter writer = mapper.writer(pp);
        OutputStreamWriter output = newFile(filePath);
        writer.writeValue(output, object);
        output.close();
    }

    public void writeObjectsToJsonFileAsActual(String expectedFilePath, Object object) throws IOException {
        expectedFilePath = path.buildFilePath(expectedFilePath);
        expectedFilePath = buildActualPathFromExpectedPath(expectedFilePath);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        OutputStreamWriter output = newFile(expectedFilePath);
        writer.writeValue(output, object);
        output.close();
    }

    public <T> void writeObjectsToCSVFileAsActual(String expectedFilePath, Class<T> clazz, List<T> objectList, char separator) throws IOException {
        expectedFilePath = path.buildFilePath(expectedFilePath);
        expectedFilePath = buildActualPathFromExpectedPath(expectedFilePath);

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withoutQuoteChar();
        schema = schema.withColumnSeparator(separator);

        // Output writer
        ObjectWriter writer = mapper.writer(schema);
        OutputStreamWriter output = newFile(expectedFilePath);
        writer.writeValue(output, objectList);
        output.close();
    }

    private String buildActualPathFromExpectedPath(String expectedFilePath) {
        String[] names = expectedFilePath.split("/|\\\\");
        names[names.length - 2] = "actualData";
        return String.join("/", names);
    }

    private OutputStreamWriter newFile(String filePath) throws FileNotFoundException {
        File tempFile = new File(filePath);
        tempFile.getParentFile().mkdirs();
        FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
        return new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
    }
}
