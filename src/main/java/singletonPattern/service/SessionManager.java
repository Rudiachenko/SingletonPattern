package singletonPattern.service;

import singletonPattern.dto.Session;
import singletonPattern.dto.User;
import singletonPattern.exception.SessionCreationException;
import singletonPattern.interfaces.AccessChecker;
import lombok.Getter;

import javax.inject.Inject;

@Getter
public class SessionManager {
    private final AccessChecker accessChecker;

    @Inject
    public SessionManager(AccessCheckerImpl accessChecker) {
        this.accessChecker = accessChecker;
    }

    public Session createSession(User user, String accessedPath) {
        if (accessChecker.mayAccess(user, accessedPath)) {
            return new Session(accessChecker, ((AccessCheckerImpl) accessChecker).getConfig());
        } else {
            throw new SessionCreationException(String.format("Unable to create session. User rights are not sufficient: %s " +
                    "or accessed path is wrong: %s", user.getAccessLevel(), accessedPath));
        }
    }
}
