package singletonPattern.interfaces;

import singletonPattern.dto.User;

public interface AccessChecker {
    boolean mayAccess(User user, String path);
}
