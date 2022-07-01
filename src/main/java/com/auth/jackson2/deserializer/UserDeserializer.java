package com.auth.jackson2.deserializer;

import com.auth.model.User;
import com.auth.model.UserAuthority;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(@NotNull JsonParser jp, DeserializationContext deserializationContext)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode authoritiesNode = readJsonNode(jsonNode, "authorities");
        Long id = readJsonNode(jsonNode, "id").asLong();
        String username = readJsonNode(jsonNode, "username").asText();
        String passwordNode = readJsonNode(jsonNode, "password").asText();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();
        return new User(id, username, passwordNode, enabled, getUserAuthorities(mapper, authoritiesNode));
    }

    private JsonNode readJsonNode(@NotNull JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

    private @NotNull Set<UserAuthority> getUserAuthorities(ObjectMapper mapper, JsonNode authoritiesNode)
            throws IOException {
        Set<UserAuthority> userAuthorities = new HashSet<>();
        if (authoritiesNode != null) {
            if (authoritiesNode.isArray()) {
                for (final JsonNode objNode : authoritiesNode) {
                    if (objNode.isArray()) {
                        ArrayNode arrayNode = (ArrayNode) objNode;
                        for (JsonNode elementNode : arrayNode) {
                            UserAuthority userAuthority =
                                    mapper.readValue(elementNode.traverse(mapper), UserAuthority.class);
                            userAuthorities.add(userAuthority);
                        }
                    }
                }
            }
        }
        return userAuthorities;
    }
}
