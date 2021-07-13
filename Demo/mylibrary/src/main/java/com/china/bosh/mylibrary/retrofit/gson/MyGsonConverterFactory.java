package com.china.bosh.mylibrary.retrofit.gson;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author lzq
 * @date 2021/7/12
 */
public class MyGsonConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static MyGsonConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and decoding from JSON
     * (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static MyGsonConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyGsonConverterFactory(gson);
    }

    private final Gson gson;

    private MyGsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof ConverterType) {
                ConverterType converterType = (ConverterType) annotation;
                Type type1 = new ParameterizedType() {
                    @NonNull
                    @Override
                    public Type[] getActualTypeArguments() {
                        return new Type[]{converterType.entity(), converterType.map(), converterType.qvo(),
                                converterType.vo(), converterType.rows()};
                    }
                    @NonNull
                    @Override
                    public Type getRawType() {
                        return ResponseDataT.class;
                    }

                    @Override
                    public Type getOwnerType() {
                        return null;
                    }
                };
                TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type1));
                return new MyGsonResponseBodyConverter<>(gson, adapter);

            }
        }
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type,
            Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations,
            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonRequestBodyConverter<>(gson, adapter);
    }
}
