Lottie for React Native, [iOS](https://github.com/airbnb/lottie-ios), and [Android](https://github.com/airbnb/lottie-android)
===
## Notes (Its ugly, But I needed Blur to work for Both Android and IOS)
Just used this and had to tweak it to my liking. To many alterations on local before I got the chance to fork it. Sorry.
Had to expose the lib to be able to alter and combine some functions.
Ugly I know. Sorry about that. My Bad.

But wanted to put it up here if ppl want to grab what they can from it.

Exposed some more functions, Play , Reset , Resume and Pause. Android And IOS
Since I needed this to work with [BlurView](https://github.com/react-native-community/react-native-blur) which didn´t work with Android (Only first frame) I exposed a new param to the default element to allow a native Blur on Android (StrokeContent, FillContent and some more elements)

IOS got the same param but it´s a dead one (Can be added basically the same way for IOS). I combined the solution with BlurView for IOS only.

Example usage:
```js
<LottieView
	style={}
	source={this.animationString} //json
	ref={ el => {
		this.animationView = el;
	}}
	loop={ true }
	speed={ this.state.animationSpeed }
	blur={ this.props.activeAnimation.an.b}
/>
{ (Platform.OS === 'ios') &&
	<BlurView
		style={[styles.fullscreen, {
		backgroundColor: 'transparent',
		}]}
		blurType="light"
		blurAmount={this.props.activeAnimation.an.b}
	/>
}
```

Anyway.. Sorry about the mess. Hope anyone find it helpful.
#

[![npm Version](https://img.shields.io/npm/v/lottie-react-native.svg)](https://www.npmjs.com/package/lottie-react-native) [![License](https://img.shields.io/npm/l/lottie-react-native.svg)](https://www.npmjs.com/package/lottie-react-native) [![Build Status](https://travis-ci.org/airbnb/lottie-react-native.svg)](https://travis-ci.org/airbnb/lottie-react-native)

Lottie component for React Native (iOS and Android)

Lottie is a mobile library for Android and iOS that parses [Adobe After Effects](http://www.adobe.com/products/aftereffects.html) animations exported as JSON with [bodymovin](https://github.com/bodymovin/bodymovin) and renders them natively on mobile!

For the first time, designers can create **and ship** beautiful animations without an engineer painstakingly recreating it by hand. They say a picture is worth 1,000 words so here are 13,000:

# View documentation, FAQ, help, examples, and more at [airbnb.io/lottie](http://airbnb.io/lottie/)

![Example1](docs/gifs/Example1.gif)


![Example2](docs/gifs/Example2.gif)


![Example3](docs/gifs/Example3.gif)


![Community](docs/gifs/Community%202_3.gif)


![Example4](docs/gifs/Example4.gif)