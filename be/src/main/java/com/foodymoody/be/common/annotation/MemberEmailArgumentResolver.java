package com.foodymoody.be.common.annotation;

import com.foodymoody.be.common.util.HttpHeaderParser;
import com.foodymoody.be.common.util.HttpHeaderType;
import com.foodymoody.be.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class MemberEmailArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MemberEmail.class);
    }

    @Override
    public String resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String header = webRequest.getHeader(HttpHeaderType.AUTHORIZATION.NAME);
        String token = HttpHeaderParser.parse(header, HttpHeaderType.AUTHORIZATION);
        return jwtUtil.extractEmail(token);
    }
}
