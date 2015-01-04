package com.deanoj.nowon.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by deano on 01/01/15.
 */
public class NetworkSingleton {
    private static NetworkSingleton ourInstance;

    public static synchronized NetworkSingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new NetworkSingleton(context);
        }
        return ourInstance;
    }

    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private NetworkSingleton(Context context) {//
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
