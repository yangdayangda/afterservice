package com.example.afterservice.config;


import com.example.afterservice.common.intercept.JwtFilter;
import com.example.afterservice.shiro.realm.Jwtrealm;
import com.example.afterservice.shiro.realm.Passwordrealm;
import com.example.afterservice.shiro.realm.UserModularRealmAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());

//        自定义一个名叫jwt的过滤器，添加进去
        HashMap<String, Filter> filter = new HashMap<>();
        filter.put("jwt",new JwtFilter());
        bean.setFilters(filter);

        Map<String, String> filterMap = new LinkedHashMap<>();
//        以下这些请求不会进入jwt
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/webjars/**","anon");
        filterMap.put("/v2/**","anon");
        filterMap.put("/swagger-ui.html/**","anon");
        filterMap.put("/user/loginByPassword","anon");
        filterMap.put("/user/getCode","anon");
        filterMap.put("/user/registerUser","anon");
        filterMap.put("/user/loginByCode","anon");

//        对所有的请求进行jwt验证
        filterMap.put("/**","jwt");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(userModularRealmAuthenticator());
        ArrayList<Realm> realms = new ArrayList<Realm>();
        realms.add(jwtRealm());
        realms.add(passwordRealm());
        securityManager.setRealms(realms);

        /*
         * 关闭shiro自带的session，详情见文档
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);


        return securityManager;
    }

    @Bean
    public UserModularRealmAuthenticator userModularRealmAuthenticator(){
        UserModularRealmAuthenticator userModularRealmAuthenticator = new UserModularRealmAuthenticator();
        userModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return userModularRealmAuthenticator;
    }


    @Bean
    public Passwordrealm passwordRealm(){
        Passwordrealm passwordRealm = new Passwordrealm();

        HashedCredentialsMatcher Matcher = new HashedCredentialsMatcher("md5");
        Matcher.setHashIterations(1024);
        passwordRealm.setCredentialsMatcher(Matcher);

        return passwordRealm;
    }

    @Bean
    public Jwtrealm jwtRealm(){
        Jwtrealm jwtRealm = new Jwtrealm();
        jwtRealm.setCacheManager(new EhCacheManager());
        jwtRealm.setCachingEnabled(true);
        jwtRealm.setAuthenticationCachingEnabled(true);
        jwtRealm.setAuthenticationCacheName("Authentication");
        jwtRealm.setAuthorizationCachingEnabled(true);
        jwtRealm.setAuthorizationCacheName("Authorization");
        return jwtRealm;
    }


    /**
     * 以下Bean开启shiro权限注解
     *
     * @return DefaultAdvisorAutoProxyCreator
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator creator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
