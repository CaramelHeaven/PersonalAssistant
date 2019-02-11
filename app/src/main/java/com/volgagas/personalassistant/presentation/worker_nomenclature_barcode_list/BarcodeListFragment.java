package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.presenter.BarcodeListPresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.presenter.BarcodeListView;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:01, 06/02/2019.
 */
public class BarcodeListFragment extends BaseFragment implements BarcodeListView {

    private BarcodeAdapter adapter;

    private RecyclerView recyclerView;
    private TextView tvCount;

    @InjectPresenter
    BarcodeListPresenter presenter;

    public static BarcodeListFragment newInstance() {

        Bundle args = new Bundle();

        BarcodeListFragment fragment = new BarcodeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nomenclature_barcode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCount = view.findViewById(R.id.tv_count);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new BarcodeAdapter(new ArrayList<>());

        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.GONE);

        adapter.setMyOnItemClickListener(position -> adapter.removeValueByPos(position));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_left);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onDestroyView() {
        tvCount = null;
        recyclerView = null;
        super.onDestroyView();
    }

    @Override
    public void stateOfLayout(Boolean bool) {
        if (bool) {
            recyclerView.setVisibility(View.VISIBLE);
            Timber.d("BOOL CHECKED");
            runLayoutAnimation(recyclerView);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateItem(Barcode barcode) {
        adapter.addValue(barcode, adapter.getItemCount() - 1);

        tvCount.setText("Добавлено: " + String.valueOf(adapter.getItemCount()));
    }

    @Override
    public void grabData() {
        CachePot.getInstance().putBarcodeCacheList(adapter.getBarcodeList());
        RxBus.getInstance().passDataToCommonChannel(Constants.PASS_DATA_BARCODE);
    }
}
