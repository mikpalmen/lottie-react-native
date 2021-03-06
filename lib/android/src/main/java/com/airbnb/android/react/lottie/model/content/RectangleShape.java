package com.airbnb.android.react.lottie.model.content;

import android.graphics.PointF;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.LottieDrawable;
import com.airbnb.android.react.lottie.animation.content.Content;
import com.airbnb.android.react.lottie.animation.content.RectangleContent;
import com.airbnb.android.react.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatableValue;
import com.airbnb.android.react.lottie.model.layer.BaseLayer;

import org.json.JSONObject;

public class RectangleShape implements ContentModel {
  private final String name;
  private final AnimatableValue<PointF, PointF> position;
  private final AnimatablePointValue size;
  private final AnimatableFloatValue cornerRadius;

  private RectangleShape(String name, AnimatableValue<PointF, PointF> position,
      AnimatablePointValue size, AnimatableFloatValue cornerRadius) {
    this.name = name;
    this.position = position;
    this.size = size;
    this.cornerRadius = cornerRadius;
  }

  static class Factory {
    private Factory() {
    }

    static RectangleShape newInstance(JSONObject json, LottieComposition composition) {
      return new RectangleShape(
          json.optString("nm"),
          AnimatablePathValue.createAnimatablePathOrSplitDimensionPath(
              json.optJSONObject("p"), composition),
          AnimatablePointValue.Factory.newInstance(json.optJSONObject("s"), composition),
          AnimatableFloatValue.Factory.newInstance(json.optJSONObject("r"), composition));
    }
  }

  public String getName() {
    return name;
  }

  public AnimatableFloatValue getCornerRadius() {
    return cornerRadius;
  }

  public AnimatablePointValue getSize() {
    return size;
  }

  public AnimatableValue<PointF, PointF> getPosition() {
    return position;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new RectangleContent(drawable, layer, this);
  }

  @Override public String toString() {
    return "RectangleShape{" + "cornerRadius=" + cornerRadius.getInitialValue() +
        ", position=" + position +
        ", size=" + size +
        '}';
  }
}
