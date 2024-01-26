package com.foodymoody.be.store.application.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreDetailsResponse {

    private String name;
    // TODO
//    private int rating;
//    private boolean hearted;
//    private int feedCount;
    private String address;
    private String roadAddress;
    private String phone;
    private Double x;
    private Double y;
    private boolean closed;

}
