package com.dryanlam.spring.bdd.project.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Path {

    private static final Logger LOGGER = LoggerFactory.getLogger(Path.class.getCanonicalName());

    public String buildFilePath(String filePath) {
        String fullPath;
        Pattern p = Pattern.compile("^[a-zA-Z]:|^/|^\\\\");
        Matcher m = p.matcher(filePath);
        if (m.find()) {
            fullPath = filePath;
            LOGGER.info("PATHNAME: {}", fullPath);
        } else {
            fullPath = Paths.get(System.getProperty("user.dir"), filePath).toString();
            LOGGER.info("FILENAME: {}", fullPath);
        }
        return fullPath;
    }
}
