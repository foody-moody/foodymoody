package com.foodymoody.be.common.config;

import com.foodymoody.be.common.annotation.CurrentMemberIdArgumentResolver;
import com.foodymoody.be.common.annotation.MemberEmailArgumentResolver;
import com.foodymoody.be.common.annotation.MemberIdArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ArgumentResolverConfig implements WebMvcConfigurer {

    private final MemberIdArgumentResolver memberIdArgumentResolver;
    private final MemberEmailArgumentResolver memberEmailArgumentResolver;
    private final CurrentMemberIdArgumentResolver currentMemberIdArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberIdArgumentResolver);
        resolvers.add(memberEmailArgumentResolver);
        resolvers.add(currentMemberIdArgumentResolver);
    }
}
