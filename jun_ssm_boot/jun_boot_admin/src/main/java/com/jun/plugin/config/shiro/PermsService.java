package com.jun.plugin.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

@Component("perms")
public class PermsService {
	
	public boolean hasPerm(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}
}
