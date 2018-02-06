package com.airbnb.android.react.lottie.model.animatable;

import android.graphics.PointF;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.animation.Keyframe;
import com.airbnb.android.react.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.android.react.lottie.animation.keyframe.PointKeyframeAnimation;
import com.airbnb.android.react.lottie.animation.keyframe.StaticKeyframeAnimation;
import com.airbnb.android.react.lottie.model.PointFFactory;

import org.json.JSONObject;

import java.util.List;

public class AnimatablePointValue extends BaseAnimatableValue<PointF, PointF> {
  private AnimatablePointValue(List<Keyframe<PointF>> keyframes, PointF initialValue) {
    super(keyframes, initialValue);
  }

  @Override public BaseKeyframeAnimation<PointF, PointF> createAnimation() {
    if (!hasAnimation()) {
      return new StaticKeyframeAnimation<>(initialValue);
    } else {
      return new PointKeyframeAnimation(keyframes);
    }
  }

  public static final class Factory {
    private Factory() {
    }

    public static AnimatablePointValue newInstance(JSONObject json, LottieComposition composition) {
      AnimatableValueParser.Result<PointF> result = AnimatableValueParser
          .newInstance(json, composition.getDpScale(), composition, PointFFactory.INSTANCE)
          .parseJson();
      return new AnimatablePointValue(result.keyframes, result.initialValue);
    }
  }
}
