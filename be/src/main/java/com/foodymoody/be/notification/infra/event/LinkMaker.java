package com.foodymoody.be.notification.infra.event;

public class LinkMaker {

    private LinkMaker() {
        throw new IllegalStateException("Utility class");
    }

    public static String makeFeedLink(String id) {
        return String.format("https://foodymoody.site/api/feeds/%s", id);
    }
}
