package ggn.home.help.Features.TestPackage;

import ggn.home.help.Features.Internal.Base.BasePresenter;

/**
 * Created by G-Expo on 03 Mar 2017.
 */

public class TestPresenter extends BasePresenter<TestView>
{

    public void buttonClicked() {
        //perform your operation and send back control to activity ,using Viewable.
        getView().buttonClickedDone();
    }
}
