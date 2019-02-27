package com.volgagas.personalassistant.presentation.about_user_certificates.presenter;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:08, 27/02/2019.
 */
public interface CertificatesView extends BaseView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showItems(List<PersonCertificates> values);
}
