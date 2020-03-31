package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component(value="permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator {
    public PermissionEvaluatorImpl() {}

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    public boolean hasAccessRole(UserAccount userAccount, String uri) {
        return userAccount.getAccessibleeUris().contains(uri);
    }
}
