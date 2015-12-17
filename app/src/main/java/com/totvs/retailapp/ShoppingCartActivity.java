package com.totvs.retailapp;

import android.os.Bundle;

import com.totvs.retailapp.daos.ShoppingCartDao;
import com.totvs.retailapp.models.StoreModel;
import com.totvs.retailapp.views.ShoppingCartView;

public class ShoppingCartActivity extends AppRetailActivity {

    protected ShoppingCartView shoppingCartView;
    protected ShoppingCartDao shoppingCartDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        this.activityName = SHOPPINGCARTACTIVITY;

        shoppingCartView = new ShoppingCartView(this, this.getWindow().getDecorView().getRootView());
        shoppingCartDao = new ShoppingCartDao(this, shoppingCartView);

        shoppingCartDao.getByUser(currentUser.getObjectId(), StoreModel.getInstance().getId());

    }
}
