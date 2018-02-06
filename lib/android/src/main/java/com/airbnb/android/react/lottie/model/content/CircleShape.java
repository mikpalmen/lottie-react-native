package com.airbnb.android.react.lottie.model.content;

import android.graphics.PointF;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.LottieDrawable;
import com.airbnb.android.react.lottie.animation.content.Content;
import com.airbnb.android.react.lottie.animation.content.EllipseContent;
import com.airbnb.android.react.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatableValue;
import com.airbnb.android.react.lottie.model.layer.BaseLayer;

import org.json.JSONObject;

public class CircleShape implements ContentModel {
  private final String name;
  private final AnimatableValue<PointF, PointF> position;
  private final AnimatablePointValue size;
  private final boolean isReversed;

  private CircleShape(String name, AnimatableValue<PointF, PointF> position,
      AnimatablePointValue size, boolean isReversed) {
    this.name = name;
    this.position = position;
    this.size = size;
    this.isReversed = isReversed;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new EllipseContent(drawable, layer, this);
  }

  static class Factory {
    private Factory() {
    }

    static CircleShape newInstance(JSONObject json, LottieComposition composition) {
      return new CircleShape(
          json.optString("nm"),
          AnimatablePathValue
              .createAnimatablePathOrSplitDimensionPath(json.optJSONObject("p"), composition),
          AnimatablePointValue.Factory.newInstance(json.optJSONObject("s"), composition),
          // "d" is 2 for normal and 3 for reversed.
          json.optInt("d", 2) == 3);
    }
  }

  public String getName() {
    return name;
  }

  public AnimatableValue<PointF, PointF> getPosition() {
    return position;
  }

  public AnimatablePointValue getSize() {
    return size;
  }

  public boolean isReversed() {
    return isReversed;
  }
}
