package com.volgagas.personalassistant.presentation.projects.query_create;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.projects.query_create.presenter.QueryCreatePresenter;
import com.volgagas.personalassistant.presentation.projects.query_create.presenter.QueryCreateView;

import java.util.Calendar;

public class QueryCreateActivity extends MvpAppCompatActivity implements DatePickerDialog.OnDateSetListener, QueryCreateView {

    private Button btnDestination, btnDatePicker, btnApply;
    private EditText etDescription;
    private TextInputEditText etTitle;
    private Toolbar toolbar;

    @InjectPresenter
    QueryCreatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_create);
        etDescription = findViewById(R.id.et_description);
        etTitle = findViewById(R.id.et_title);
        toolbar = findViewById(R.id.toolbar);
        btnDestination = findViewById(R.id.btn_destination);
        btnDatePicker = findViewById(R.id.btn_date_picker);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        provideClickListeners();
    }

    private void provideClickListeners() {
        btnDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provideDatePicker();
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QueryCreateActivity.this, "completed", Toast.LENGTH_SHORT).show();
            }
        });
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

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
