package com.dharani.design;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by LENOVO on 07/04/17.
 */

public class ProductModelAsync  extends AsyncTask<String,Void,String> {

    //get subcategory id and pass to url
    private static String productModelUrl = "http://www.gopinath.pe.hu/product_model.php?subcat_id=SC001";
    private String productName,productPrice,productStack;
    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        RequestBody body;
        Response response;
        String jsondata = null;
        OkHttpClient client = new OkHttpClient();

        body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(productModelUrl)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                jsondata = response.body().string();
                Log.e("JsonData","productModel --> "+ jsondata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsondata;
    }

    @Override
    protected void onPostExecute(String jsondata) {
        //super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            Log.d("JSONArray","subCategory"+jsonObject);

            if (jsonArray != null){
                for (int i = 0; i < jsonArray.length();i++){
                    JSONObject  object = jsonArray.getJSONObject(i);
                    productName = object.getString("product_name");
                    productPrice = object.getString("price");
                    productStack = object.getString("stack");

                    Log.e("SubDepartment ",""+productName);
                    Log.e("SubCategory ID ",""+productPrice);
                    Log.e("SubCategory Name ",""+productStack);
                }
            }else {
                Log.e("NULL data","");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}