package com.airbnb.android.react.lottie.model.animatable;

import com.airbnb.android.react.lottie.animation.Keyframe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("unchecked")
public abstract class BaseAnimatableValue<V, O> implements AnimatableValue<V, O> {
  final List<Keyframe<V>> keyframes;
  final V initialValue;

  /**
   * Create a default static animatable path.
   */
  @SuppressWarnings("unchecked")
  BaseAnimatableValue(V initialValue) {
    this(Collections.<Keyframe<V>>emptyList(), initialValue);
  }

  @SuppressWarnings("unchecked")
  BaseAnimatableValue(List<Keyframe<V>> keyframes, V initialValue) {
    this.keyframes = keyframes;
    this.initialValue = initialValue;
  }

  /**
   * Convert the value type of the keyframe to the value type of the animation. Often, these
   * are the same type.
   */
  O convertType(V value) {
    //noinspection unchecked
    return (O) value;
  }

  @Override
  public boolean hasAnimation() {
    return !keyframes.isEmpty();
  }

  public O getInitialValue() {
    return convertType(initialValue);
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("parseInitialValue=").append(initialValue);
    if (!keyframes.isEmpty()) {
      sb.append(", values=").append(Arrays.toString(keyframes.toArray()));
    }
    return sb.toString();
  }
}
