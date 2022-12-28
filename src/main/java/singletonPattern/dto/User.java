package singletonPattern.dto;

import singletonPattern.config.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class User {
    private AccessLevel accessLevel;
}
