package com.stayhome.web.rest;

import com.stayhome.security.AuthoritiesConstants;
import com.stayhome.security.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithUserPrincipalMockUser.Factory.class)
public @interface WithUserPrincipalMockUser {

    long organizationId() default 1;

    String username() default "username";

    String password() default "secret";

    String[] roles() default { AuthoritiesConstants.USER };

    class Factory implements WithSecurityContextFactory<WithUserPrincipalMockUser> {

        @Override
        public SecurityContext createSecurityContext(WithUserPrincipalMockUser annotation) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UserPrincipal principal = new UserPrincipal(annotation.username(),
                annotation.organizationId(),
                Arrays.asList(annotation.roles())
            );

            context.setAuthentication(new UsernamePasswordAuthenticationToken(principal, annotation.password(),
                Stream.of(annotation.roles())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet())
            ));

            return context;
        }
    }
}
