package com.volgagas.personalassistant.presentation.projects.query_create;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.DatePicker;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.projects.query_create.presenter.QueryCreatePresenter;
import com.volgagas.personalassistant.presentation.projects.query_create.presenter.QueryCreateView;
import com.volgagas.personalassistant.utils.views.ControlledSwipeViewPager;

import java.util.Calendar;

import timber.log.Timber;

public class QueryCreateActivity extends MvpAppCompatActivity implements DatePickerDialog.OnDateSetListener, QueryCreateView {

//    private Button btnDestination, btnDatePicker, btnApply;
//    private EditText etDescription;
//    private TextInputEditText etTitle;
//    private Toolbar toolbar;

    private ControlledSwipeViewPager vpContainer;
    private QueryCreateAdapter adapterQuery;

    @InjectPresenter
    QueryCreatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uniform_request_create);
        vpContainer = findViewById(R.id.vp_container);
        adapterQuery = new QueryCreateAdapter(getSupportFragmentManager());

        vpContainer.setAdapter(adapterQuery);

        vpContainer.enableScroll(false);

        vpContainer.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    vpContainer.enableScroll(true);
                } else {
                    vpContainer.enableScroll(false);
                }
            }
        });

        //        etDescription = findViewById(R.id.et_description);
//        etTitle = findViewById(R.id.et_title);
//        toolbar = findViewById(R.id.toolbar);
//        btnDestination = findViewById(R.id.btn_destination);
//        btnDatePicker = findViewById(R.id.btn_date_picker);
//        btnApply = findViewById(R.id.btn_apply);


        //  setSupportActionBar(toolbar);


        // provideClickListeners();
    }

//    private void provideClickListeners() {
//        btnDestination.setOnClickListener(v -> {
//            RecipientDialogFragment recipientFragment = RecipientDialogFragment.newInstance();
//            recipientFragment.show(getSupportFragmentManager(), null);
//        });
//
//        btnDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                provideDatePicker();
//            }
//        });
//
//        btnApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(QueryCreateActivity.this, "completed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void sendResult(User user) {
        Timber.d("uqqqser: " + user.toString());
    }

    private void provideDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, 0, QueryCreateActivity.this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "" + dayOfMonth + "/" + month + "/" + year;

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showNextPage() {
        Timber.d("SHOW NEXT PAGE");
        vpContainer.setCurrentItem(1);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
