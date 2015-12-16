package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.totvs.retailapp.daos.ShoppingCartDao;
import com.totvs.retailapp.models.ShoppingCartModel;
import com.totvs.retailapp.views.ShoppingCartView;

public class ShoppingCartActivity extends AppRetailActivity {

    protected ShoppingCartView shoppingCartView;
    protected ShoppingCartDao shoppingCartDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        this.activityName = purchaseDetailActivity;

        shoppingCartView = new ShoppingCartView(this, this.getWindow().getDecorView().getRootView());
        shoppingCartDao = new ShoppingCartDao(this, shoppingCartView);

        shoppingCartDao.get(ShoppingCartModel.getInstance().getId());

    }
}
