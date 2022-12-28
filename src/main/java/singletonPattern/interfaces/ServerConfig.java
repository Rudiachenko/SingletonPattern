package singletonPattern.interfaces;

import singletonPattern.config.AccessLevel;
import singletonPattern.dto.User;

public interface ServerConfig {
    AccessLevel getAccessLevel(User u);
}
