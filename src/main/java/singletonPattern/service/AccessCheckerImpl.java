package singletonPattern.service;

import singletonPattern.config.AccessLevel;
import singletonPattern.dto.User;
import singletonPattern.interfaces.AccessChecker;
import singletonPattern.interfaces.ServerConfig;
import com.google.inject.Inject;
import lombok.Getter;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Singleton
public class AccessCheckerImpl implements AccessChecker {
    private final int id;   // for testing purpose
    private final ServerConfig config;
    private final Map<String, List<AccessLevel>> protectedUrls = new HashMap<>();

    @Inject
    public AccessCheckerImpl(ServerConfig config) {
        this.config = config;
        this.id = (int) (Math.random() * 10);
        protectedUrls.put("/users/all", List.of(AccessLevel.ADMIN));
        protectedUrls.put("/users/delete", List.of(AccessLevel.ADMIN));
        protectedUrls.put("/shopping-carts/products", List.of(AccessLevel.USER));
        protectedUrls.put("/user/orders", List.of(AccessLevel.USER));
    }

    public boolean mayAccess(User user, String path) {
        AccessLevel userLevel = config.getAccessLevel(user);
        if (Objects.isNull(userLevel)) {
            return false;
        }

        if (protectedUrls.containsKey(path)) {
            List<AccessLevel> accessLevels = protectedUrls.get(path);
            return accessLevels.contains(userLevel);
        }
        return false;
    }
}
