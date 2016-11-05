package com.github.ihandy.testproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_QUERY = "SEARCH_QUERY";

    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.email, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String searchQuery = null;
        if (savedInstanceState != null && savedInstanceState.containsKey(SEARCH_QUERY)) {
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        }
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        if (!TextUtils.isEmpty(searchQuery)) {
            Bundle args = new Bundle(1);
            args.putString(SEARCH_QUERY, searchQuery);
            mainActivityFragment.setArguments(args);

            if (mSearchView != null) {
                mSearchView.requestFocus();
                mSearchView.setQuery(searchQuery, false);
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mainActivityFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener((SearchView.OnQueryTextListener) getSupportFragmentManager().findFragmentById(R.id.fragment));
        mSearchView.setSubmitButtonEnabled(true);

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String lastQuery = ((MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getLastQuery();
        if (mSearchView != null && !mSearchView.isIconified() && !TextUtils.isEmpty(lastQuery)) {
            outState.putString(SEARCH_QUERY, lastQuery);
        }
    }

}
