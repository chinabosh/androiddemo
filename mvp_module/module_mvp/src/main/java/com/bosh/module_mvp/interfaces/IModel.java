package com.bosh.module_mvp.interfaces;

import com.bosh.module_mvp.entity.LoginUser;

/**
 * @author bosh
 * @date 2019-07-17
 */
public interface IModel {
    void setUser(LoginUser user);
    LoginUser getUser();
}
