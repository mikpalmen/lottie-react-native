package com.airbnb.android.react.lottie.animation.keyframe;

import com.airbnb.android.react.lottie.animation.Keyframe;

import java.util.List;

public abstract class KeyframeAnimation<T> extends BaseKeyframeAnimation<T, T> {
  KeyframeAnimation(List<? extends Keyframe<T>> keyframes) {
    super(keyframes);
  }
}
