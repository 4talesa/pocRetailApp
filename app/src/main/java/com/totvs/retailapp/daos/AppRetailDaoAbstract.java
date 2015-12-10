package com.totvs.retailapp.daos;

import android.content.Context;

import com.android.volley.Response;
import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by rond.borges on 10/12/2015.
 */
public abstract class AppRetailDaoAbstract<T> {

    protected final Context context;
    protected final AppRetailViewAbsctrat view;

    public AppRetailDaoAbstract(Context context, AppRetailViewAbsctrat view) {
        this.context = context;
        this.view = view;
    }

    public abstract void save(T model);

    public abstract void delete(T model);

    public abstract List<T> list();

    public abstract void find (String id);

    public abstract void get(String id);

}
