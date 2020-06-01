package info.u250.c2d.engine.transitions;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.Gdx;
import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;
/**
 * the scene zoom in 
 * @author lycying@gmail.com
 */
final class TransitionSceneZoomIn extends Transition {
	public TransitionSceneZoomIn(){
	}
	
	@Override
	protected void doTransition(final int halfDurationMillis) {
		outgoing.hide();
		Tween
		.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, halfDurationMillis).target(10f)
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				doSetMainScene(incoming);
				Tween
				.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, halfDurationMillis).target(1)
				.setCallback(new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						
						Gdx.input.setInputProcessor(incoming.getInputProcessor());
						incoming.show();
						reset();
					}
				}).start(Engine.getTweenManager());
			}
		}).start(Engine.getTweenManager());
	}
	@Override
	public void render(float delta) {
		Engine.getMainScene().render(delta);
	}
}
