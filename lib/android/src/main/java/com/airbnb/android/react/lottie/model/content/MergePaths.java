package com.airbnb.android.react.lottie.model.content;

import android.support.annotation.Nullable;
import android.util.Log;

import com.airbnb.android.react.lottie.L;
import com.airbnb.android.react.lottie.LottieDrawable;
import com.airbnb.android.react.lottie.animation.content.Content;
import com.airbnb.android.react.lottie.animation.content.MergePathsContent;
import com.airbnb.android.react.lottie.model.layer.BaseLayer;

import org.json.JSONObject;


public class MergePaths implements ContentModel {

  public enum MergePathsMode {
    Merge,
    Add,
    Subtract,
    Intersect,
    ExcludeIntersections;

    private static MergePathsMode forId(int id) {
      switch (id) {
        case 1:
          return Merge;
        case 2:
          return Add;
        case 3:
          return Subtract;
        case 4:
          return Intersect;
        case 5:
          return ExcludeIntersections;
        default:
          return Merge;
      }
    }
  }

  private final String name;
  private final MergePathsMode mode;

  private MergePaths(String name, MergePathsMode mode) {
    this.name = name;
    this.mode = mode;
  }

  public String getName() {
    return name;
  }

  public MergePathsMode getMode() {
    return mode;
  }

  @Override @Nullable public Content toContent(LottieDrawable drawable, BaseLayer layer) {
    if (!drawable.enableMergePathsForKitKatAndAbove()) {
      Log.w(L.TAG, "Animation contains merge paths but they are disabled.");
      return null;
    }
    return new MergePathsContent(this);
  }

  @Override
  public String toString() {
    return "MergePaths{" + "mode=" +  mode + '}';
  }

  static class Factory {
    private Factory() {
    }

    static MergePaths newInstance(JSONObject json) {
      return new MergePaths(json.optString("nm"), MergePathsMode.forId(json.optInt("mm", 1)));
    }
  }
}
