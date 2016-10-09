package com.example.jessyuan.alldemo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jessyuan.alldemo.R;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

/**
 * Created by Jess Yuan on 08/10/2016.
 */

public class AIFragment extends BaseFragment {
    @Override
    public int setLayoutViewId() {
        return R.layout.fragment_ai_demo;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AIConfiguration config = new AIConfiguration("b9bb353b57514a8b86fb4aaf470c2fa7",
                AIConfiguration.SupportedLanguages.ChineseChina,
                AIConfiguration.RecognitionEngine.System);

        final AIDataService aiDataService = new AIDataService(getActivity(), config);

        final AIRequest aiRequest = new AIRequest();
        aiRequest.setQuery("帮到你");

        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... requests) {
                final AIRequest request = requests[0];
                try {
                    final AIResponse response = aiDataService.request(aiRequest);
                    return response;
                } catch (AIServiceException e) {
                }
                return null;
            }
            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                if (aiResponse != null) {
                    // process aiResponse here
                }
            }
        }.execute(aiRequest);
    }
}
