package com.xiong.stockdiagnosticdiary.wwwwwww;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.*;

//HttpUtils.post("https://www.antsmakemoneyapps.xyz/api/api",
//            object : HttpUtils.Callback {
//                override fun onResponse(response: String?) {
//                    Log.e(TAG, "onResponse: $response")
//                    val gson=Gson()
//                    val fromJson = gson.fromJson(response, JsonObject::class.java)
//                    val asString = fromJson.get("retCode").asString
//                    if (asString=="0"){
//                        val asJsonArray = fromJson.get("data").asJsonArray
//                        val get = asJsonArray.get(1).asString
//                        val decode = Base64.decode(get, Base64.DEFAULT)
//                        val decode2 = Base64.decode(decode, Base64.DEFAULT)
//                        val url=String(decode2,Charsets.UTF_8)
//                        Log.e(TAG, "onResponse: $url", )
//                    }
//                }
//
//                override fun onFailure(e: IOException?) {
//
//                }
//
//            })
public class HttpUtils {
    static String urls="http://cn-gx-plc-1.openfrp.top:45721/lei?method=";
     public interface Callback {
        void onResponse(String response);

        void onFailure(IOException e);
    }

    public static void post(String method, Callback callback) throws UnsupportedEncodingException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = "";
        String encodedMethod = URLEncoder.encode(method, "UTF-8");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(urls+encodedMethod)
                .get()
                //.addHeader("Content-Type", "application/json")
                //.addHeader("X-requested-with", "com.xiong.stockdiagnosticdiary")
                .build();



        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.e(TAG, "onResponse: "+response );
                callback.onResponse(responseBody);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }
        });
    }
    public static void login(String method,String phone, Callback callback) throws UnsupportedEncodingException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String encodedMethod = URLEncoder.encode(method, "UTF-8");

        Request request = new Request.Builder()
                .url(urls+encodedMethod+"&phone="+phone)
                .get()
                .build();



        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.e(TAG, "onResponse: "+response );
                callback.onResponse(responseBody);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }
        });
    }
    public static void yuYue(String itemId,String hairstylistId,String shopId,String memberId, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
         RequestBody requestBody = new FormBody.Builder()
                .add("item", itemId)
                .add("hairstylist", hairstylistId)
                .add("shop", shopId)
                .add("member", memberId)
                .build();

            // 构建请求对象
        Request request = new Request.Builder()
                .url(urls+"addAppointInfo")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.e(TAG, "onResponse: "+response );
                callback.onResponse(responseBody);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }
        });
    }
    public static void register(String phone,String name,String birthday, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody requestBody = new FormBody.Builder()
                .add("phone", phone)
                .add("name", name)
                .add("balance", "1")//开卡费用
                .add("birthday", birthday)
                .add("member_grade_id", "1")//初级会员
                .add("info", "")
                .build();

        // 构建请求对象
        Request request = new Request.Builder()
                .url(urls+"addMember")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.e(TAG, "onResponse: "+response );
                callback.onResponse(responseBody);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }
        });
    }
}

