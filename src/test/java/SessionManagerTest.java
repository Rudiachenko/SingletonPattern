import singletonPattern.config.AccessLevel;
import singletonPattern.dto.Session;
import singletonPattern.dto.User;
import singletonPattern.exception.SessionCreationException;
import singletonPattern.service.DefaultModule;
import singletonPattern.service.SessionManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SessionManagerTest {
    private static final String PATH_FOR_SUFFICIENT_RIGHTS = "/users/all";
    private static final String PATH_FOR_INSUFFICIENT_RIGHTS = "/users/delete";
    private static final String INCORRECT_PATH = "";
    Injector injector = Guice.createInjector(new DefaultModule());

    @Test
    public void shouldCreateSession() {
        SessionManager sessionManager = injector.getInstance(SessionManager.class);
        SessionManager sessionManager2 = injector.getInstance(SessionManager.class);

        User user = new User(AccessLevel.ADMIN);
        Session session = sessionManager.createSession(user, PATH_FOR_SUFFICIENT_RIGHTS);
        Session session2 = sessionManager2.createSession(user, PATH_FOR_SUFFICIENT_RIGHTS);

        assertThat(session.getAccessChecker(), is(session2.getAccessChecker()));
        assertThat(session.getConfig(), is(session2.getConfig()));
    }

    @Test
    public void shouldThrowExceptions() {
        SessionManager sessionManager = injector.getInstance(SessionManager.class);
        SessionManager sessionManagerCopy = injector.getInstance(SessionManager.class);

        User user = new User(AccessLevel.USER);
        assertThrows(SessionCreationException.class, () -> sessionManager.createSession(user, PATH_FOR_INSUFFICIENT_RIGHTS));
        assertThrows(SessionCreationException.class, () -> sessionManagerCopy.createSession(user, PATH_FOR_INSUFFICIENT_RIGHTS));

        User user2 = new User(null);
        assertThrows(SessionCreationException.class, () -> sessionManager.createSession(user2, PATH_FOR_INSUFFICIENT_RIGHTS));
        assertThrows(SessionCreationException.class, () -> sessionManagerCopy.createSession(user2, PATH_FOR_INSUFFICIENT_RIGHTS));

        assertThrows(SessionCreationException.class, () -> sessionManager.createSession(user2, INCORRECT_PATH));
        assertThrows(SessionCreationException.class, () -> sessionManagerCopy.createSession(user2, INCORRECT_PATH));
    }
}

