package com.netcracker.interviewschedulerauthorization.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecurityAccessContext {
    private static final ObjectMapper mapper = new ObjectMapper();

    private SecurityAccessObject subject;
    private SecurityAccessObject object;
    private String action;

    public SecurityAccessContext(JsonNode subject, JsonNode object, String action) {
        this.subject = new SecurityAccessObject(subject);
        this.object = new SecurityAccessObject(object);
        this.action = action;
    }

    public class SecurityAccessObject {
        JsonNode node;

        public SecurityAccessObject(JsonNode node) {
            this.node = node;
        }

        public String get(String path) {
            if (path.charAt(0) != '/') path = "/" + path;
            return node.at(path).asText("");
        }

        public List getArray(String path) {
            if (path.charAt(0) != '/') path = "/" + path;
            return mapper.convertValue(node.at(path), ArrayList.class);
        }
    }

    public SecurityAccessObject getObject() {
        return object;
    }

    public SecurityAccessObject getSubject() {
        return subject;
    }

    public String getAction() {
        return action;
    }
}
