package com.foodymoody.be.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated @CurrentMemberId를 사용하면 MemberId 타입으로 가져올 수 있습니다
 * */
@Deprecated(forRemoval = true)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberId {

}
