package com.airbnb.android.react.lottie.model.animatable;

import android.graphics.Path;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.animation.Keyframe;
import com.airbnb.android.react.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.android.react.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.android.react.lottie.animation.keyframe.StaticKeyframeAnimation;
import com.airbnb.android.react.lottie.model.content.ShapeData;
import com.airbnb.android.react.lottie.utils.MiscUtils;

import org.json.JSONObject;

import java.util.List;

public class AnimatableShapeValue extends BaseAnimatableValue<ShapeData, Path> {
  private final Path convertTypePath = new Path();

  private AnimatableShapeValue(List<Keyframe<ShapeData>> keyframes, ShapeData initialValue) {
    super(keyframes, initialValue);
  }

  @Override public BaseKeyframeAnimation<ShapeData, Path> createAnimation() {
    if (!hasAnimation()) {
      return new StaticKeyframeAnimation<>(convertType(initialValue));
    } else {
      return new ShapeKeyframeAnimation(keyframes);
    }
  }

  @Override Path convertType(ShapeData shapeData) {
    convertTypePath.reset();
    MiscUtils.getPathFromData(shapeData, convertTypePath);
    return convertTypePath;
  }

  public static final class Factory {
    private Factory() {
    }

    public static AnimatableShapeValue newInstance(JSONObject json, LottieComposition composition) {
      AnimatableValueParser.Result<ShapeData> result = AnimatableValueParser
          .newInstance(json, composition.getDpScale(), composition, ShapeData.Factory.INSTANCE)
          .parseJson();
      return new AnimatableShapeValue(result.keyframes, result.initialValue);
    }
  }
}
