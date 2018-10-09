package com.darorman.gm3yaorman.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.darorman.gm3yaorman.R;
import com.darorman.gm3yaorman.base.BaseFragment;
import com.darorman.gm3yaorman.utilities.Constants;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragment extends BaseFragment {

    @BindView(R.id.web)
    WebView webView;

    @Override
    protected void initializeDagger() {

    }

    @Override
    protected void intializePresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_donation;
    }

    @Override
    protected void initialization() {
        webView.loadUrl(Constants.DONATION_URL);
    }
}
