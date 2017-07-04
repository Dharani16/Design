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

public class SubCategoryAsync extends AsyncTask<String,Void,String> {

    //get category id and pass to url
    private static String categoryUrl = "http://www.gopinath.pe.hu/subcategory.php?name=CT001";
    private String subDept,subCatid,subCat,subStatus;
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
                .url(categoryUrl)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                jsondata = response.body().string();
                Log.e("JsonData","subcategory --> "+ jsondata);
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
                    subDept = object.getString("department");
                    subCatid = object.getString("cat_id");
                    subCat = object.getString("category");
                    subStatus = object.getString("status");

                    Log.e("SubDepartment ",""+subDept);
                    Log.e("SubCategory ID ",""+subCatid);
                    Log.e("SubCategory Name ",""+subCat);
                    Log.e("Subcat status ",""+subStatus);
                }
            }else {
                Log.e("NULL data","");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
