package com.airbnb.android.react.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.Log;

import com.airbnb.android.react.lottie.L;
import com.airbnb.android.react.lottie.LottieDrawable;
import com.airbnb.android.react.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.android.react.lottie.model.content.ShapeFill;
import com.airbnb.android.react.lottie.model.layer.BaseLayer;

import java.util.ArrayList;
import java.util.List;

public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener {
  private final Path path = new Path();
  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final String name;
  private float blur;
  private final List<PathContent> paths = new ArrayList<>();
  private final BaseKeyframeAnimation<Integer, Integer> colorAnimation;
  private final BaseKeyframeAnimation<Integer, Integer> opacityAnimation;

  private final LottieDrawable lottieDrawable;

  public FillContent(final LottieDrawable lottieDrawable, BaseLayer layer, ShapeFill fill) {
    name = fill.getName();

    this.lottieDrawable = lottieDrawable;
    if (fill.getColor() == null || fill.getOpacity() == null ) {
      colorAnimation = null;
      opacityAnimation = null;
      return;
    }

    blur = fill.getBlur();
    path.setFillType(fill.getFillType());
    if(lottieDrawable.getBlur() == 0){
      paint.setMaskFilter(null);
    } else {
      paint.setMaskFilter(new BlurMaskFilter(lottieDrawable.getBlur(), BlurMaskFilter.Blur.NORMAL));
    }

    colorAnimation = fill.getColor().createAnimation();
    colorAnimation.addUpdateListener(this);
    layer.addAnimation(colorAnimation);
    opacityAnimation = fill.getOpacity().createAnimation();
    opacityAnimation.addUpdateListener(this);
    layer.addAnimation(opacityAnimation);
  }

  @Override public void onValueChanged() {
    lottieDrawable.invalidateSelf();
  }

  @Override public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
    for (int i = 0; i < contentsAfter.size(); i++) {
      Content content = contentsAfter.get(i);
      if (content instanceof PathContent) {
        paths.add((PathContent) content);
      }
    }
  }

  @Override public String getName() {
    return name;
  }

  @Override public void addColorFilter(@Nullable String layerName, @Nullable String contentName,
      @Nullable ColorFilter colorFilter) {
    paint.setColorFilter(colorFilter);
  }

  @Override public void setBlur(float blur) {
    if(blur == 0){
      paint.setMaskFilter(null);
    } else {
      paint.setMaskFilter(new BlurMaskFilter(blur, BlurMaskFilter.Blur.NORMAL));
    }

  }

  @Override public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
    L.beginSection("FillContent#draw");
    paint.setColor(colorAnimation.getValue());
    int alpha = (int) ((parentAlpha / 255f * opacityAnimation.getValue() / 100f) * 255);
    paint.setAlpha(alpha);

    path.reset();
    for (int i = 0; i < paths.size(); i++) {
      path.addPath(paths.get(i).getPath(), parentMatrix);
    }

    canvas.drawPath(path, paint);
    L.endSection("FillContent#draw");
  }

  @Override public void getBounds(RectF outBounds, Matrix parentMatrix) {
    path.reset();
    for (int i = 0; i < paths.size(); i++) {
      this.path.addPath(paths.get(i).getPath(), parentMatrix);
    }
    path.computeBounds(outBounds, false);
    // Add padding to account for rounding errors.
    outBounds.set(
        outBounds.left - 1,
        outBounds.top - 1,
        outBounds.right + 1,
        outBounds.bottom + 1
    );
  }
}