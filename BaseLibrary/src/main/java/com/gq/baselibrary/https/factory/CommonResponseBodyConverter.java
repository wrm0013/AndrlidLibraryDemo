package com.gq.baselibrary.https.factory;

import com.google.gson.Gson;
import com.gq.baselibrary.https.Logger;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Charles on 2016/3/17.
 */
class CommonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    CommonResponseBodyConverter(Gson gson, Type type) {

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Logger.e(response);
        return (T) response;
    }
}
