package ggn.home.help.Features.Internal.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ggn.home.help.Features.Internal.Base.Contract.Presentable;
import ggn.home.help.Features.Internal.Base.Contract.Viewable;
import ggn.home.help.UtilsG.SharedPrefHelper;

/**
 * Base Fragment with genric presenter and viewable implemented,
 * @param <T> presenter class.
 * We are using same methods ,as we have used in Activity, So that both shares same structure and become easy to code and understand.
 */
public abstract class BaseFragment<T extends Presentable> extends Fragment implements Viewable<T>
{
    protected T                presenter;
    protected View             view;
    protected SharedPrefHelper sharedPrefHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart()
    {
        super.onStart();
        getPresenter().onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onCreateFragmentG();
        setRetainInstance(true);
    }

    @Override
    public SharedPrefHelper getLocalData()
    {
        if (sharedPrefHelper == null) {
            sharedPrefHelper = new SharedPrefHelper(getActivityG());
        }
        return sharedPrefHelper;
    }

    @Override
    public Context getActivityG()
    {
        return getActivity();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(getLayoutId(), container, false);

        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onViewCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroyView()
    {
        getPresenter().detachView();
        super.onDestroyView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop()
    {
        getPresenter().onStop();
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy()
    {
        getPresenter().detachView();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void onResume()
    {
        getPresenter().onResume();
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message)
    {
        if (getParentView() != null) {
            Snackbar.make(getParentView(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public View getParentView()
    {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLoading()
    {
        // no-op by default
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideLoading()
    {
        // no-op by default
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getPresenter()
    {
        return presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void injectPresenter(T presenter)
    {
        this.presenter = presenter;
    }

    /**
     * @return layout id , used for setContentView();
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * injectPresenter( @link Presentable);
     * attachView(this); in this method.
     */
    protected abstract void onCreateFragmentG();

}
