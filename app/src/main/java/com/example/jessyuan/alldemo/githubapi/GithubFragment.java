package com.example.jessyuan.alldemo.githubapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.mylibrary.common.CommonRCLVAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by JessYuan on 09/12/2016.
 */

public class GithubFragment extends BaseToolbarFragment implements GithubContract.GithubView {

    @BindView(R.id.rcv_repository)
    RecyclerView recyvlerView;

    private static final String TAG = "GithubFragment";

    private CommonRCLVAdapter<Repository> adapter;
    private GithubPresenter mPresenter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionsMenu: ");
        inflater.inflate(R.menu.menu_searchview, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mPresenter.searchRepository(newText);
                return true;
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fragment_github_api);

        setRecyclerViewAndAdapter();

        mPresenter = new GithubPresenter(getActivity(), this);
    }


    private void setRecyclerViewAndAdapter() {
        adapter = new CommonRCLVAdapter<Repository>(getActivity(),android.R.layout.simple_list_item_1, null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, Repository data) {
                holder.getTextViewById(android.R.id.text1).setText(data.getFull_name());
            }
        };

        recyvlerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyvlerView.setAdapter(adapter);
    }

    @Override
    public void showTitle(String str) {
        getToolbar().setTitle(str);
    }

    @Override
    public void showRepository(List<Repository> list) {
        adapter.setData(list);
    }
}
