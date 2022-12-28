package singletonPattern.dto;

import singletonPattern.interfaces.AccessChecker;
import singletonPattern.interfaces.ServerConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Session {
    private final AccessChecker accessChecker;
    private final ServerConfig config;
}
