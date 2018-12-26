package com.volgagas.personalassistant.presentation.order_new_purchase;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.order_purchase.NewOrder;
import com.volgagas.personalassistant.models.model.order_purchase.Order;
import com.volgagas.personalassistant.presentation.order_new_bottom_sheet.OrderNewBottomFragment;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchasePresenter;
import com.volgagas.personalassistant.presentation.order_new_purchase.presenter.OrderNewPurchaseView;
import com.volgagas.personalassistant.utils.bus.GlobalBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

public class OrderNewPurchaseActivity extends MvpAppCompatActivity implements OrderNewPurchaseView<Order> {

    private ViewPager vpContainer;
    private Button btnCountItems, btnConfirm;
    private RelativeLayout rlContainer;
    private BottomSheetBehavior bottomSheetBehavior;

    private OrderViewPager pagerAdapter;

    @InjectPresenter
    OrderNewPurchasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_new_purchase);
        vpContainer = findViewById(R.id.vp_container);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnCountItems = findViewById(R.id.btn_count_items);
        rlContainer = findViewById(R.id.rl_container);
        bottomSheetBehavior = BottomSheetBehavior.from(rlContainer);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, OrderNewBottomFragment.newInstance())
                .commit();

        btnCountItems.setOnClickListener(v -> {
            Timber.d("lalal");
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            GlobalBus.getEventBus().post(presenter.getChosenOrders());
        });

        btnConfirm.setOnClickListener(v -> {
            if (presenter.getChosenOrders().size() == 0) {
                Toast.makeText(OrderNewPurchaseActivity.this, "Корзина пуста", Toast.LENGTH_SHORT).show();
            } else {
                Timber.d("kek");
            }
        });

        pagerAdapter = new OrderViewPager(getSupportFragmentManager());
        vpContainer.setAdapter(pagerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getEventBus().unregister(this);
        super.onStop();
    }

    /* 0 - remove, 1 - add
     * */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void addedTaskToList(NewOrder newOrder) {
        Timber.d("checking: " + newOrder);
        if (newOrder.getStatus() == 0) {
            presenter.removeNewOrder(newOrder);
        } else {
            presenter.addNewOrder(newOrder);
        }

        btnCountItems.setText(String.valueOf(presenter.getChosenOrders().size()));
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void closeOrderSheetFragment(String close) {
        if (close.equals("CLOSE")) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
