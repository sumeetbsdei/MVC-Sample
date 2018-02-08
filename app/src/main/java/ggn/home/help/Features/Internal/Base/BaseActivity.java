package ggn.home.help.Features.Internal.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ggn.home.help.Features.Internal.Base.Contract.Presentable;
import ggn.home.help.Features.Internal.Base.Contract.Viewable;
import ggn.home.help.R;
import ggn.home.help.UtilsG.SharedPrefHelper;

/**
 * Created by G-Expo on 03 Mar 2017.
 * BaseActivity with genric presenter and viewable implemented,
 */
public abstract class BaseActivity<T extends Presentable> extends AppCompatActivity implements Viewable<T>
{
    protected T presenter;

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
    protected void onResume()
    {
        super.onResume();
        getPresenter().onResume();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause()
    {
        getPresenter().onPause();
        super.onPause();
    }


    /**
     * set up toolbar with back button and custom title.
     * @param title
     */
    public void setupToolbar(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        onCreateActivityG();
        getPresenter().onViewCreated();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(String message)
    {
        if (setParentView() != null)
        {
            Snackbar.make(setParentView(), message, Snackbar.LENGTH_LONG).show();
        }
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
     * @return {@link SharedPrefHelper} contains all local data in it.
     */
    @Override
    public SharedPrefHelper getLocalData()
    {
        return new SharedPrefHelper(getApplicationContext());
    }

    /**
     * @return layout id , used for setContentView();
     */
    protected abstract int setLayoutId();

    protected View setParentView()
    {
        return findViewById(android.R.id.content);
    }

    /**
     * injectPresenter( @link Presentable);
     * attachView(this); in this method.
     */
    protected abstract void onCreateActivityG();
}
