package ggn.home.help.Features.Internal.Base;

import android.support.annotation.NonNull;

import ggn.home.help.Features.Internal.Base.Contract.Presentable;
import ggn.home.help.Features.Internal.Base.Contract.Viewable;

/**
 * Base for all the presenter classes.
 * Contains basic methods for presenter class.
 * @param <T> Viewable interface.
 */
public class BasePresenter<T extends Viewable> implements Presentable<T>
{
    private T viewable;

    @Override
    public void onStart()
    {
        // No-op by default
    }

    @Override
    public void onViewCreated()
    {
//        views are created ,now its time to initialize them..
        if (getView() != null) {
            getView().initViews();
        }
    }

    @Override
    public void onResume()
    {
        // No-op by default
    }

    @Override
    public void onPause()
    {
        // No-op by default
    }

    @Override
    public void onStop()
    {
        // No-op by default
    }


    /**
     * attach viewable to the presenter class.(Viewable is used by Presenter to communicate with Activity)
     * @param viewable Viewable
     */
    @Override
    public void attachView(@NonNull T viewable)
    {
        this.viewable = viewable;
    }


    /**
     * detach the view from presenter.
     */
    @Override
    public void detachView()
    {
        this.viewable = null;
    }


    /**
     * get the Viewable interface.
     * @return {@link Viewable}
     */
    @Override
    public T getView()
    {
        return viewable;
    }
}
