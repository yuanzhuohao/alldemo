package com.example.jessyuan.alldemo.githubapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.jessyuan.alldemo.R;
import com.example.jessyuan.alldemo.api.GithubService;
import com.example.jessyuan.alldemo.base.BaseToolbarFragment;
import com.example.jessyuan.alldemo.component.DaggerNetworkComponent;
import com.example.jessyuan.alldemo.component.NetworkComponent;
import com.example.jessyuan.alldemo.model.Repository;
import com.example.jessyuan.alldemo.model.User;
import com.example.jessyuan.alldemo.module.ApplicationModule;
import com.example.jessyuan.alldemo.module.GithubServiceModule;
import com.example.jessyuan.alldemo.module.NetworkModule;
import com.example.jessyuan.alldemo.ui.VerticalItemDecoration;
import com.example.mylibrary.common.CommonRCLVAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by JessYuan on 09/12/2016.
 */

public class GithubFragment extends BaseToolbarFragment implements GithubContract.GithubView {

    @BindView(R.id.rcv_repository)
    RecyclerView mRecyclerView;
    @BindView(R.id.sv_github_search)
    SearchView mSearchView;
    @BindView(R.id.spr_github_search)
    Spinner mSearchSpinner;
    @BindView(R.id.spr_github_sorted)
    Spinner mSortedSpinner;

    private static final String TAG = "GithubFragment";

    private String searchKey = "";
    private String sorted = "default";

    private CommonRCLVAdapter<Repository> repositoryAdapter;
    private CommonRCLVAdapter<User> userAdapter;

    private Subject<String> mSubject = PublishSubject.create();

    @Inject
    GithubPresenter mPresenter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionsMenu: ");
        inflater.inflate(R.menu.menu_searchview, menu);
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

        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(GithubService.GITHUB_API))
                .applicationModule(new ApplicationModule(getActivity().getApplication()))
                .build();

        DaggerGithubComponent.builder()
                .networkComponent(networkComponent)
                .githubModule(new GithubModule(this))
                .build()
                .inject(this);

        // initial layout view
        init();
        setSubject();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void init() {
        setRecyclerView();
        setSpinner();
        setSearchView();
    }

    private void setSearchView() {
        mSearchView.onActionViewExpanded();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    if (repositoryAdapter != null) {
                        repositoryAdapter.clearData();
                    }

                    if (userAdapter != null) {
                        userAdapter.clearData();
                    }
                }

                if ("User".equals(searchKey)) {
                    if ("default".equals(sorted)) {
                        mPresenter.searchUser(query);
                    } else  {
                        mPresenter.searchUser(query, sorted);
                    }
                } else {
                    if ("default".equals(sorted)) {
                        mPresenter.searchRepository(query);
                    } else {
                        mPresenter.searchRepository(query, sorted);
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSubject.onNext(newText);

                return true;
            }
        });
    }

    private void setSubject() {
        mSubject.debounce(800, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (TextUtils.isEmpty(s)) {
                            if (repositoryAdapter != null) {
                                repositoryAdapter.clearData();
                            }

                            if (userAdapter != null) {
                                userAdapter.clearData();
                            }
                        }

                        if ("User".equals(searchKey)) {
                            mPresenter.searchUser(s);
                        } else {
                            mPresenter.searchRepository(s);
                        }
                    }
                });
    }

    private void setSpinner() {
        mSearchSpinner.setAdapter(getArrayAdapter(R.array.search_item));

        // search key listener
        mSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] item = getActivity().getResources().getStringArray(R.array.search_item);
                searchKey = item[position];
                if ("User".equals(searchKey)) {
                    setUserAdapter();

                    mSortedSpinner.setAdapter(getArrayAdapter(R.array.user_sorted));
                } else {
                    setRepositoryAdapter();

                    mSortedSpinner.setAdapter(getArrayAdapter(R.array.repository_sorted));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // sorted listener
        mSortedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] items = searchKey.equals("User") ?
                        getActivity().getResources().getStringArray(R.array.user_sorted) :
                        getActivity().getResources().getStringArray(R.array.repository_sorted);

                sorted = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayAdapter<String> getArrayAdapter(int arrayId) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                getActivity().getResources().getStringArray(arrayId)
        );

        return adapter;
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new VerticalItemDecoration(
                getActivity().getResources().getDimensionPixelSize(R.dimen.divider_height)));
    }


    /**
     * when spinner item was change, then recyclerview changes adapater
     */
    private void setRepositoryAdapter() {
        repositoryAdapter = new CommonRCLVAdapter<Repository>(getActivity(),android.R.layout.simple_list_item_1, null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, final Repository data) {
                holder.getTextViewById(android.R.id.text1).setText(data.getFullName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.openRepository(data, getActivity());
                    }
                });
            }
        };

        mRecyclerView.setAdapter(repositoryAdapter);
    }

    /**
     * when spinner item was change, then recyclerview changes adapater
     */
    private void setUserAdapter() {
        userAdapter = new CommonRCLVAdapter<User>(getActivity(), R.layout.item_github_user, null) {
            @Override
            public void onBindViewHolder(CommonViewHolder holder, int position, final User data) {
                Glide.with(getActivity())
                        .load(data.getAvatarUrl())
                        .placeholder(R.drawable.github_placeholder)
                        .error(R.drawable.github_placeholder)
                        .into(holder.getImageViewById(R.id.iv_item_image));

                holder.getTextViewById(R.id.tv_item_username).setText(data.getLogin());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.openUser(data, getActivity());
                    }
                });
            }
        };

        mRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public void showTitle(String str) {
        getToolbar().setTitle(str);
    }

    @Override
    public void showRepository(List<Repository> list) {
        repositoryAdapter.setData(list);
    }

    @Override
    public void showUser(List<User> list) {
        userAdapter.setData(list);
    }
}
