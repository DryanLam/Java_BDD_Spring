package com.dryanlam.spring.bdd.project.common.util;

import com.dryanlam.spring.bdd.project.common.exception.ProjectException;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class Var {

    private Var() {
    }

    private static Map<String, Object> variables = new HashMap<>();

    public void storeVariable(String key, Object value) {
        variables.put(key, value);
    }

    public Object getVariable(String key) {
        key = key.replaceAll("%", "");
        if (!isKeyExist(key)) {
            throw new ProjectException("The key '" + key + "' does not exist.");
        }
        return variables.get(key);
    }

    public void clearVariables() {
        variables.clear();
    }

    public boolean isKeyExist(String key) {
        return variables.containsKey(key);
    }
}
