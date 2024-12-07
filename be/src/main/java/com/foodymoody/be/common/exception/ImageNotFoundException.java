package com.foodymoody.be.common.exception;

public class ImageNotFoundException extends ResourceNotFoundException {

    public ImageNotFoundException() {
        super(ErrorMessage.IMAGE_NOT_FOUND);
    }

}
