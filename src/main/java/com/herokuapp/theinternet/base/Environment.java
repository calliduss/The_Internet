package com.herokuapp.theinternet.base;

import org.aeonbits.owner.Config;

@Config.HotReload
@Config.Sources("classpath:${env}.properties")
public interface Environment extends Config {

    @DefaultValue("Production")
    @Key("server.name")
    String name();

    @Key("server.url")
    String url();
}