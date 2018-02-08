package ggn.home.help.Features.TestPackage;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import ggn.home.help.Features.Internal.Base.BaseFragment;
import ggn.home.help.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment<TestPresenter> implements TestView
{
    public TestFragment()
    {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_blank;
    }

    @Override
    protected void onCreateFragmentG()
    {
        injectPresenter(new TestPresenter());
        getPresenter().attachView(this);
    }

    @Override
    public void initViews()
    {
//        initialize all views here..
        Button button = (Button) getParentView().findViewById(R.id.btnTest);
        button.setText("(fragment)");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().buttonClicked();
            }
        });
    }


    @Override
    public void buttonClickedDone() {
        displayError("Snack bar.........");
    }
}
