package com.airbnb.android.react.lottie.model.content;

import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.Log;

import com.airbnb.android.react.lottie.LottieComposition;
import com.airbnb.android.react.lottie.LottieDrawable;
import com.airbnb.android.react.lottie.animation.content.Content;
import com.airbnb.android.react.lottie.animation.content.FillContent;
import com.airbnb.android.react.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.android.react.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.android.react.lottie.model.layer.BaseLayer;

import org.json.JSONObject;

public class ShapeFill implements ContentModel {
  private final boolean fillEnabled;
  private final Path.FillType fillType;
  private final String name;
  @Nullable private final AnimatableColorValue color;
  @Nullable private final AnimatableIntegerValue opacity;
  private final float blur;

  private ShapeFill(String name, boolean fillEnabled, Path.FillType fillType, float blur,
      @Nullable AnimatableColorValue color, @Nullable AnimatableIntegerValue opacity) {
    this.name = name;
    this.fillEnabled = fillEnabled;
    this.fillType = fillType;
    this.color = color;
    this.blur = blur;
    this.opacity = opacity;
  }

  static class Factory {
    private Factory() {
    }

    static ShapeFill newInstance(JSONObject json, LottieComposition composition, int inBlur) {
      AnimatableColorValue color = null;
      boolean fillEnabled;
      AnimatableIntegerValue opacity = null;
      float blur = 0;
      final String name = json.optString("nm");

      JSONObject jsonColor = json.optJSONObject("c");
      if (jsonColor != null) {
        color = AnimatableColorValue.Factory.newInstance(jsonColor, composition);
      }

      if(inBlur != 0){
        blur = inBlur;
      }

      JSONObject jsonOpacity = json.optJSONObject("o");
      if (jsonOpacity != null) {
        opacity = AnimatableIntegerValue.Factory.newInstance(jsonOpacity, composition);
      }
      fillEnabled = json.optBoolean("fillEnabled");

      int fillTypeInt = json.optInt("r", 1);
      Path.FillType fillType = fillTypeInt == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
      return new ShapeFill(name, fillEnabled, fillType, blur, color, opacity);
    }
  }

  public String getName() {
    return name;
  }

  @Nullable public AnimatableColorValue getColor() {
    return color;
  }

  @Nullable public float getBlur() {
    return blur;
  }

  @Nullable public AnimatableIntegerValue getOpacity() {
    return opacity;
  }

  public Path.FillType getFillType() {
    return fillType;
  }

  @Override public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    return new FillContent(drawable, layer, this);
  }

  @Override
  public String toString() {
    return "ShapeFill{" + "color=" +
        (color == null ? "null" :  Integer.toHexString(color.getInitialValue())) +
        ", fillEnabled=" + fillEnabled +
        ", opacity=" + (opacity == null ? "null" : opacity.getInitialValue()) +
        '}';
  }
}
