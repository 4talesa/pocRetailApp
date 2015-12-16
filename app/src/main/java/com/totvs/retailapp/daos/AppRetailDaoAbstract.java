package com.totvs.retailapp.daos;

import android.content.Context;

import com.totvs.retailapp.views.AppRetailViewAbsctrat;

import java.util.List;

/**
 * Created by rond.borges on 10/12/2015.
 */
public abstract class AppRetailDaoAbstract<T> {

    protected final Context context;
    protected final AppRetailViewAbsctrat view;
    static public final String URL_API = "http://protected-bayou-8222.herokuapp.com/api/v1/";

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
