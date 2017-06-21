package info.u250.c2d.tests.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.ParticleEffectActor;

import java.util.Random;


public class SalutParticleTest extends Engine {
    @Override
    protected EngineDrive onSetupEngineDrive() {
        return new EngineX();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private class EngineX implements EngineDrive {
        @Override
        public void onResourcesRegister(AliasResourceManager<String> reg) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public EngineOptions onSetupEngine() {
            final EngineOptions opt = new EngineOptions(new String[]{}, 800, 480);
            return opt;
        }

        @Override
        public void onLoadedResourcesCompleted() {
            final ParticleEffect particleEffect = new ParticleEffect();
            particleEffect.load(Gdx.files.internal("data/particles/D.pp"), Gdx.files.internal("data/particles/"));
            ParticleEffectActor actor = new ParticleEffectActor(particleEffect, "salut") {
                float accum = 0;
                float delay = 1;
                Random r = new Random();

                @Override
                public void act(float delta) {
                    accum += delta;
                    if (accum >= delay) {
                        accum = 0;
                        setPosition(r.nextFloat() * Engine.getWidth(), 300 + 150 * r.nextFloat());
                        delay = r.nextFloat() * 2 + 0.5f;
                        this.getEmitter().start();
                    }
                    super.act(delta);
                }
            };
            actor.setPauseWithEngine(true);//when pause , stop it


            actor.setPosition(450, 250);
            SceneStage stage = new SceneStage();
            stage.addActor(actor);
            Engine.setMainScene(stage);
        }
    }
}
