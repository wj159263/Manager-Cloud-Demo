/*
package com.wj.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

*/
/**
 * @EnableAuthorizationServer 该注解表示开启认证服务，服务可以生成token
 * 当继承了AuthorizationServerConfigurerAdapter，oauth2默认的配置就取消了，要自己设置AuthenticationManager、userDetailsService
 *//*

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "restservice";

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;


        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        UserDetailsService userDetailsServiceImpl;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(jwtAccessTokenConverter)
                    .userDetailsService(userDetailsServiceImpl);
        }



        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("isAuthenticated()");//校验token需要认证通过，可采用http basic认证
            oauthServer.allowFormAuthenticationForClients()
                    //.passwordEncoder(new BCryptPasswordEncoder())
                    .passwordEncoder(passwordEncoder)
                   .tokenKeyAccess("permitAll()")  //好像作用不大
                    .checkTokenAccess("isAuthenticated()");
        }
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("test")
                    .secret(passwordEncoder.encode("test"))
                    .authorizedGrantTypes("password","refresh_token","authorization_code")
                    //.authorizedGrantTypes("password","authorization_code","refresh_token")
                    //.authorities("USER")
                    .scopes("all")
                    .accessTokenValiditySeconds(7200)
                    //.accessTokenValiditySeconds(20)
                    .refreshTokenValiditySeconds(9000);
                   // .resourceIds(RESOURCE_ID);

        }



}*/
