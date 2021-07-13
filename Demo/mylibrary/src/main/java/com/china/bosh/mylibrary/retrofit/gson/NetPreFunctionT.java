package com.china.bosh.mylibrary.retrofit.gson;

import android.text.TextUtils;

import io.reactivex.functions.Function;

/**
 * @author lzq
 * @date 2021/7/12
 */
@SuppressWarnings("rawtypes")
public class NetPreFunctionT <T extends ResponseDataT> implements Function<T, ResponseDataT> {
    public final String SUCCESS_CODE = "10000";
    public final String LOGIN_OVER_TIME_MSG = "登陆已过期";

    @Override
    public ResponseDataT apply(T t) throws Exception {
        if (SUCCESS_CODE.equals(t.getCode())) {
            return t;
        } else if (!TextUtils.isEmpty(t.getMsg())) {
            if (LOGIN_OVER_TIME_MSG.equals(t.getMsg())) {
//                EventBusUtil.sendErrEvent(EventCode.EVENT_LOGIN_OVER_TIME, "登录过期");
            }
            throw new Exception(t.getMsg());
//            throw new ResponseCodeError(t.getMsg());
        } else {
            throw new Exception(t.getMsg());
//            throw new ResponseCodeError(t.getCode());
        }
    }
}
