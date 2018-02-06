package com.airbnb.android.react.lottie.model;

import android.content.res.Resources;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.OnCompositionLoadedListener;
import com.airbnb.android.react.lottie.model.CompositionLoader;

import org.json.JSONObject;

public final class JsonCompositionLoader extends CompositionLoader<JSONObject> {
  private final Resources res;
  private final OnCompositionLoadedListener loadedListener;

  public JsonCompositionLoader(Resources res, OnCompositionLoadedListener loadedListener) {
    this.res = res;
    this.loadedListener = loadedListener;
  }

  @Override protected LottieComposition doInBackground(JSONObject... params) {
    return LottieComposition.Factory.fromJsonSync(res, params[0]);
  }

  @Override protected void onPostExecute(LottieComposition composition) {
    loadedListener.onCompositionLoaded(composition);
  }
}
