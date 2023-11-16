package com.foodymoody.be.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.exception.ErrorResponse;
import com.foodymoody.be.common.util.HttpHeaderParser;
import com.foodymoody.be.common.util.HttpHeaderType;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RequiredArgsConstructor
public class AccessTokenFilter implements Filter {

    @Value("${filter.whitelist}")
    private final List<String> whiteList;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String customUri = extractCustomUri(httpRequest);
            if (!isInWhiteList(customUri)) {
                String header = httpRequest.getHeader(HttpHeaderType.AUTHORIZATION.headerName);
                String token = HttpHeaderParser.parse(header, HttpHeaderType.AUTHORIZATION);
                Map<String, String> parsed = jwtUtil.parseAccessToken(token);
                request.setAttribute("id", parsed.get("id"));
                request.setAttribute("email", parsed.get("email"));
            }
        } catch (JwtException e) {
            sendError(response, e, ErrorMessage.INVALID_TOKEN.getCode());
            return;
        } catch (Exception e) {
            sendError(response, e, ErrorMessage.UNAUTHORIZED.getCode());
            return;
        }

        chain.doFilter(request, response);
    }

    private void sendError(ServletResponse response, Exception e, String code) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = httpResponse.getWriter();
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), code);
        String jsonErrorResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponse);
        writer.write(jsonErrorResponse);
        writer.flush();
    }

    private String extractCustomUri(HttpServletRequest httpRequest) {
        String method = httpRequest.getMethod();
        String requestUri = httpRequest.getRequestURI();
        return String.join(" ", method, requestUri);
    }

    private boolean isInWhiteList(String uri) {
        return whiteList.stream().anyMatch(whitelist -> Pattern.matches(whitelist, uri));
    }

}
