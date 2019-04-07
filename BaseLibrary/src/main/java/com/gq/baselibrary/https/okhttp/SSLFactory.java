package com.gq.baselibrary.https.okhttp;

import com.gq.baselibrary.https.HttpManager;

import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.SSLSocketFactory;

public class SSLFactory {

    public static SSLSocketFactory getSSL() {
        InputStream inputStream = null;
        try {
            if (HttpManager.getInstance().DEBUG)
                inputStream = HttpManager.getInstance().getContext().getAssets().open("test.bks");
            else
                inputStream = HttpManager.getInstance().getContext().getAssets().open("cert12306.bks");
            SslSocketUtils.SSLParams sslParams = SslSocketUtils.getSslSocketFactory(null, "gqi.2009", new InputStream[]{inputStream});
            return sslParams.sSLSocketFactory;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
