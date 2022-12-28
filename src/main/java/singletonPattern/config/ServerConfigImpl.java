package singletonPattern.config;

import singletonPattern.dto.User;
import singletonPattern.interfaces.ServerConfig;
import lombok.Getter;

import javax.inject.Singleton;

@Getter
@Singleton
public class ServerConfigImpl implements ServerConfig {
    private final int id;   // for testing purpose

    public ServerConfigImpl() {
        this.id = (int) (Math.random() * 10);
    }

    public AccessLevel getAccessLevel(User user) {
        return user.getAccessLevel();
    }
}
