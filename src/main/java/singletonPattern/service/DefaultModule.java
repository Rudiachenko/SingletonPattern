package singletonPattern.service;

import singletonPattern.config.ServerConfigImpl;
import singletonPattern.interfaces.AccessChecker;
import singletonPattern.interfaces.ServerConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DefaultModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AccessChecker.class).to(AccessCheckerImpl.class).in(Singleton.class);
        bind(ServerConfig.class).to(ServerConfigImpl.class).in(Singleton.class);
    }
}
