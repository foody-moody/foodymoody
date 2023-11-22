package com.foodymoody.be.common.exception;

public class MoodNotFoundException extends ResourceNotFoundException{

    public MoodNotFoundException() {
        super(ErrorMessage.MOOD_NOT_FOUND);
    }
}
