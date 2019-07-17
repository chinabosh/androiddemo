package com.bosh.module_mvp.ui.base;

import com.bosh.module_mvp.entity.LoginUser;
import com.bosh.module_mvp.interfaces.IModel;

/**
 * @author bosh
 * @date 2019-07-17
 */
public class BaseModel implements IModel {
    private static LoginUser mUser;


    @Override
    public void setUser(LoginUser user) {
        mUser = user;
    }

    @Override
    public LoginUser getUser() {
        return mUser;
    }
}
