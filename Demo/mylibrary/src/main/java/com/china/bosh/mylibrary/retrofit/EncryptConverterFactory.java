package com.china.bosh.mylibrary.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * retrofit 用base64加解密
 * @author lzq
 * @date 2019-09-05
 */
public class EncryptConverterFactory extends Converter.Factory {

    public static EncryptConverterFactory create() {
        return create(new Gson());
    }

    public static EncryptConverterFactory create(Gson gson) {
        return new EncryptConverterFactory(gson);
    }

    private final Gson gson;

    private EncryptConverterFactory(Gson gson) {
        if(gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new DecodeResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EncodeRequestBodyConverter<>(adapter);
    }
}
