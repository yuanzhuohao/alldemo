package com.example.jessyuan.alldemo.interfaces;

import ai.api.model.AIError;
import ai.api.model.AIResponse;

/**
 * Created by Jess Yuan on 08/10/2016.
 */

public interface AIListener {
    void onResult(AIResponse result); // here process response
    void onError(AIError error); // here process error
    void onAudioLevel(float level); // callback for sound level visualization
    void onListeningStarted(); // indicate start listening here
    void onListeningCanceled(); // indicate stop listening here
    void onListeningFinished(); // indicate stop listening here
}
