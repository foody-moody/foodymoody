package com.foodymoody.be.common.exception;

public class MenuNotFoundException extends ResourceNotFoundException {

    public MenuNotFoundException() {
        super(ErrorMessage.MENU_NOT_FOUND);
    }

}
