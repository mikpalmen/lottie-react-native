package com.airbnb.android.react.lottie.animation.keyframe;

import com.airbnb.android.react.lottie.animation.Keyframe;
import com.airbnb.android.react.lottie.model.ScaleXY;
import com.airbnb.android.react.lottie.utils.MiscUtils;

import java.util.List;

public class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {
  public ScaleKeyframeAnimation(List<Keyframe<ScaleXY>> keyframes) {
    super(keyframes);
  }

  @Override public ScaleXY getValue(Keyframe<ScaleXY> keyframe, float keyframeProgress) {
    if (keyframe.startValue == null || keyframe.endValue == null) {
      throw new IllegalStateException("Missing values for keyframe.");
    }
    ScaleXY startTransform = keyframe.startValue;
    ScaleXY endTransform = keyframe.endValue;
    return new ScaleXY(
        MiscUtils.lerp(startTransform.getScaleX(), endTransform.getScaleX(), keyframeProgress),
        MiscUtils.lerp(startTransform.getScaleY(), endTransform.getScaleY(), keyframeProgress));
  }
}
