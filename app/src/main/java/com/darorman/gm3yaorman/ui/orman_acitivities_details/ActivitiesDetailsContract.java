package com.darorman.gm3yaorman.ui.orman_acitivities_details;

import com.darorman.gm3yaorman.base.MvpPresenter;
import com.darorman.gm3yaorman.base.MvpView;

/**
 * Created by Ahmed Mostafa on 9/4/2018.
 */
public interface ActivitiesDetailsContract {

    interface DetailsView extends MvpView{

    }

    interface DetailsPresenter extends MvpPresenter<MvpView>{}
}
