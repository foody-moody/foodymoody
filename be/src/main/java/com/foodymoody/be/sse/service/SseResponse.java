package com.foodymoody.be.sse.service;

public class SseResponse {

    private final long count;

    public SseResponse(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
