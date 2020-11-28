package com.example.afterservice.shiro.realm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.service.RolePremissionService;
import com.example.afterservice.service.UserRoleService;
import com.example.afterservice.service.UserService;
import com.example.afterservice.shiro.token.JwtToken;
import com.example.afterservice.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Jwtrealm extends AuthorizingRealm {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePremissionService rolePremissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String token=principalCollection.toString();
        DecodedJWT verify=null;
        try {
            verify = JWTUtil.verify(token);
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCode.E_100102);
        }
        String id = verify.getClaim("id").asString();
        Set<String> roles = userRoleService.getRoleById(id);
        simpleAuthorizationInfo.addRoles(roles);

        Set<String> premissions=rolePremissionService.getPreByRole(roles);
        simpleAuthorizationInfo.addStringPermissions(premissions);
        return simpleAuthorizationInfo;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        try {
            JWTUtil.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("验证错误");
        }
        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
