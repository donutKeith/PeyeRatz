package com.prtz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import KeithTools.Collider;

/**
 * Created by Keith on 8/20/2016.
 */
public class Player extends Captain implements InputProcessor{

    private boolean downIsStillPressed;
    private Vector3 cameraClickPos;

    private boolean w_pressed = false;
    private boolean a_pressed = false;
    private boolean s_pressed = false;
    private boolean d_pressed = false;
    private boolean space_pressed = false;

    public Player(PeyeRatShip ship){
        super(ship);
        Gdx.input.setInputProcessor(this);
    }

    public double lerp(double start, double end, double percentComplete){
        // Make sure percent complete does not go above 1 or below 0
        if(percentComplete > 1){
            percentComplete = 1;
        }
        if(percentComplete < 0){
            percentComplete = 0;
        }
        return (1-percentComplete)*start + percentComplete*end;
    }

    public void DriveShip(){
        if ( downIsStillPressed ){
            SteerShipTo(Gdx.input.getX(), Gdx.input.getY());
        }
        keyPressed();
    }

    private void SteerShipTo(float x, float y){
        // Assumes x and y are given in screen coordinates so we convert them to camera coordinates
        cameraClickPos = GameScreen.gameCam.unproject(new Vector3(x, y, 0));
        Vector2 shipCenter = captainsShip.GetShip().Debug_GetCenter();
        if (shipCenter.x != cameraClickPos.x || shipCenter.y != cameraClickPos.y) {
            Vector2 moveDir = new Vector2(cameraClickPos.x - shipCenter.x, cameraClickPos.y - shipCenter.y);
            moveDir.nor(); // In case the vector is pointing at an angle (!= 0, 90, 180 ,270) otherwise moving on an angle will be slightly faster
            moveDir.scl(captainsShip.GetShipSpeed() * Gdx.graphics.getDeltaTime());

            // Handle moving small amounts *******************
            if (Math.abs(moveDir.x) > Math.abs(cameraClickPos.x - shipCenter.x)){
                moveDir.x = cameraClickPos.x - shipCenter.x;
            }
            if (Math.abs(moveDir.y) > Math.abs(cameraClickPos.y - shipCenter.y)){
                moveDir.y = cameraClickPos.y - shipCenter.y;
            }
            // ***********************************************

            captainsShip.GetShip().Move(shipCenter.x + moveDir.x, shipCenter.y + moveDir.y);

        }

    }

    public void keyPressed(){
        if (a_pressed) {
            captainsShip.GetCannonBall().Move(captainsShip.GetCannonBall().Debug_GetCenter().x - 10 * Gdx.graphics.getDeltaTime(), captainsShip.GetCannonBall().Debug_GetCenter().y);
        }
        if (d_pressed) {
            captainsShip.GetCannonBall().Move(captainsShip.GetCannonBall().Debug_GetCenter().x + 10 * Gdx.graphics.getDeltaTime(), captainsShip.GetCannonBall().Debug_GetCenter().y);
        }
        if (w_pressed) {
            captainsShip.GetCannonBall().Move(captainsShip.GetCannonBall().Debug_GetCenter().x, captainsShip.GetCannonBall().Debug_GetCenter().y + 10 * Gdx.graphics.getDeltaTime());
        }
        if (s_pressed) {
            captainsShip.GetCannonBall().Move(captainsShip.GetCannonBall().Debug_GetCenter().x, captainsShip.GetCannonBall().Debug_GetCenter().y - 10 * Gdx.graphics.getDeltaTime());
        }
        if (space_pressed) {
            captainsShip.GetShip().Rotate(1);
        }
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.A) {
            a_pressed = true;
        }
        if (keycode == Input.Keys.D) {
            d_pressed = true;
        }
        if (keycode == Input.Keys.W) {
            w_pressed = true;
        }
        if (keycode == Input.Keys.S) {
            s_pressed = true;
        }
        if (keycode == Input.Keys.SPACE) {
            space_pressed = true;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            a_pressed = false;
        }
        if (keycode == Input.Keys.D) {
            d_pressed = false;
        }
        if (keycode == Input.Keys.W) {
            w_pressed = false;
        }
        if (keycode == Input.Keys.S) {
            s_pressed = false;
        }
        if (keycode == Input.Keys.SPACE) {
            space_pressed = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

       // cameraClickPos = GameScreen.gameCam.unproject(new Vector3(screenX, screenY, 0));
        downIsStillPressed = true;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        downIsStillPressed = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //SteerShipTo(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
