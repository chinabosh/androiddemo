package com.china.bosh.mylibrary.retrofit;

import android.util.Base64;

import com.china.bosh.mylibrary.utils.Base64Util;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * encode request body by base64
 * @author lzq
 * @date 2019-09-05
 */
public class EncodeRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private TypeAdapter<T> adapter;

    public EncodeRequestBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }
    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), Base64Util.encode(adapter.toJson(value)));
    }
}
