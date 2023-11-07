package com.foodymoody.be.common.filter;

import com.foodymoody.be.common.util.HttpHeaderType;
import com.foodymoody.be.common.util.HttpHeaderParser;
import com.foodymoody.be.auth.util.JwtUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter implements Filter {

    @Value("${filter.whitelist}")
    private final List<String> whiteList;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String customUri = extractCustomUri(httpRequest);
        if(!isInWhiteList(customUri)) {
            String header = httpRequest.getHeader(HttpHeaderType.AUTHORIZATION.NAME);
            String token = HttpHeaderParser.parse(header, HttpHeaderType.AUTHORIZATION);
            jwtUtil.validateAccessToken(token);
        }
        chain.doFilter(request, response);
    }

    private String extractCustomUri(HttpServletRequest httpRequest) {
        String method = httpRequest.getMethod();
        String requestUri = httpRequest.getRequestURI();
        return String.join(" ", method, requestUri);
    }

    private boolean isInWhiteList(String uri) {
        return whiteList.contains(uri);
    }

}
