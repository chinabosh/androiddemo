package com.china.bosh.mylibrary.retrofit;

import com.china.bosh.mylibrary.utils.Base64Util;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * decode response body by base64
 * @author lzq
 * @date 2019-09-05
 */
public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private TypeAdapter<T> adapter;

    public DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        return adapter.fromJson(Base64Util.decode(value.string()));
    }
}
