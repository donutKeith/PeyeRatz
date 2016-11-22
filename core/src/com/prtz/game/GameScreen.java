package com.prtz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Keith on 8/20/2016.
 */
public class GameScreen implements Screen {
    private static final float ASPECT_RATIO =  (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
    public static final float GAME_HEIGHT = 100;
    public static OrthographicCamera gameCam;
    private Viewport gameViewport;
    private SpriteBatch mainBatch;

    private PeyeRatShip playerShip;
    private Player player;

    GameScreen (){
        mainBatch = new SpriteBatch();
        // ****** Camera and Viewport variables ******************************************************
        gameCam = new OrthographicCamera();

        gameViewport = new StretchViewport(GAME_HEIGHT  * ASPECT_RATIO, GAME_HEIGHT, gameCam);
        gameViewport.apply();

        gameCam.translate(gameCam.viewportWidth / 2f, gameCam.viewportHeight / 2f);


        playerShip = new PeyeRatShip(gameCam.viewportWidth /2f, gameCam.viewportHeight /2f,"badlogic.jpg", 0);
        player = new Player(playerShip);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCam.update();
        player.DriveShip();

        mainBatch.setProjectionMatrix(gameCam.combined);
        mainBatch.begin();
        playerShip.Draw(mainBatch);
        mainBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
