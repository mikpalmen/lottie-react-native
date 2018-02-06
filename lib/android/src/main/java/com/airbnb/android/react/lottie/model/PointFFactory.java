package com.airbnb.android.react.lottie.model;

import android.graphics.PointF;

import com.airbnb.android.react.lottie.model.animatable.AnimatableValue;
import com.airbnb.android.react.lottie.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class PointFFactory implements AnimatableValue.Factory<PointF> {
  public static final PointFFactory INSTANCE = new PointFFactory();

  private PointFFactory() {
  }

  @Override public PointF valueFromObject(Object object, float scale) {
    if (object instanceof JSONArray) {
      return JsonUtils.pointFromJsonArray((JSONArray) object, scale);
    } else if (object instanceof JSONObject) {
      return JsonUtils.pointFromJsonObject((JSONObject) object, scale);
    } else {
      throw new IllegalArgumentException("Unable to parse point from " + object);
    }
  }
}
