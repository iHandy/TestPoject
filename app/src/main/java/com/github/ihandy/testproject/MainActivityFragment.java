package com.github.ihandy.testproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ihandy.testproject.model.GifItem;
import com.github.ihandy.testproject.model.Giphy;
import com.github.ihandy.testproject.model.GiphyImageResponse;

import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.github.ihandy.testproject.MainActivity.SEARCH_QUERY;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private GifsRecyclerViewAdapter mAdapter;

    private GiphyService mService;

    private String mLastQuery;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        mAdapter = new GifsRecyclerViewAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

        mService = RestUtils.createRetrofitService(GiphyService.SERVICE_ENDPOINT);

        if (getArguments() != null && getArguments().containsKey(SEARCH_QUERY)) {
            mLastQuery = getArguments().getString(SEARCH_QUERY);
        }

        if (TextUtils.isEmpty(mLastQuery)) {
            mService.getTrending(GiphyService.PUBLIC_API_KEY)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Giphy>() {
                        @Override
                        public final void onCompleted() {
                            // do nothing
                        }

                        @Override
                        public final void onError(Throwable e) {
                        }

                        @Override
                        public final void onNext(Giphy response) {
                            fillData(response);
                        }
                    });
        } else {
            onQueryTextSubmit(mLastQuery);
        }
    }

    private void fillData(@NonNull Giphy response) {
        List<GifItem> data = new LinkedList<>();
        for (GiphyImageResponse giphyImageResponse : response.getData()) {
            data.add(giphyImageResponse.getImages().getOriginal());
        }
        mAdapter.setData(data);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.replaceAll(" ", "+");
        mService.getBySearch(GiphyService.PUBLIC_API_KEY, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Giphy>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                    }

                    @Override
                    public final void onNext(Giphy response) {
                        fillData(response);
                    }
                });

        mRecyclerView.scrollToPosition(0);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mLastQuery = newText;
        return false;
    }

    public String getLastQuery() {
        return mLastQuery;
    }
}
