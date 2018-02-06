package com.airbnb.android.react.lottie.model;

import android.os.AsyncTask;

import com.airbnb.android.react.lottie.Cancellable;
import com.airbnb.android.react.lottie.LottieComposition;

public abstract class CompositionLoader<Params> extends AsyncTask<Params, Void, LottieComposition>
    implements Cancellable {
  @Override public void cancel() {
    cancel(true);
  }
}
