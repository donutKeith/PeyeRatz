package com.prtz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Keith on 8/19/2016.
 * Pickups are objects that are floating in the water (on a map "LevelMap") waiting for the ship to
 * hit. When the ship hits these objects an effect is applied to the ship. Some are good
 * and some are bad. After the object is hit it is removed from the map.
 */
public abstract class PickUps {

    // ATTRIBUTES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    private Sprite image;                   // Graphical representation of the pickup
                                            // Also holds drawing information
                                            // (x, y position, rotation, scale etc.)

    private boolean shipHit = false;        // Flag to signal if this has been hit by a ship. If so
                                            // stop drawing it on the map. You may still play 1
                                            // final animation or something which would get done in
                                            // the "DrawAndApplyEffect" but it will not be on
                                            // map anymore

    private boolean effectDone = false;     // Flag to signal if the effect of this pick up has been
                                            // applied and the drawing animation of the ship hitting
                                            // it is finished

    protected PeyeRatShip shipThatHitThis;  // The ship that hit this PickUp

    // *********************************************************************************************
    // Constructor
    // *********************************************************************************************
    public PickUps(String imgName, float startX, float startY) {
        image = new Sprite(new Texture(Gdx.files.internal(imgName)));
        image.setCenter(startX, startY);
    }
    // *********************************************************************************************

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    // ---------------------------------------------------------------------------------------------
    // Abstract Methods that need to be defined per child class
    // ---------------------------------------------------------------------------------------------
    // Method should apply the effect of this PickUp to "shipThatHitThis" and draw hit animation if
    //  there is one.
    // ** IMPORTANT ** IT IS ASSUMED THAT THIS METHOD WILL SET "effectDone" FLAG TO "TRUE"
    public abstract void DrawAndApplyEffect(PeyeRatShip ship);
    protected abstract void DrawPickUp();
    protected abstract void Dispose();
    // ---------------------------------------------------------------------------------------------

    // =============================================================================================
    // Concrete Methods that ALL pickups use. (aka things that all pick ups do/need)
    // =============================================================================================
    // This method is to be called when a ship hits this PickUp. The parameter passed in should be
    //  the ship that hit this PickUp
    public void Hit(PeyeRatShip ship) {
        shipHit = true;
        shipThatHitThis = ship;
    }

    // This method draws the PickUp each frame until a ship hits hit by calling "Hit" which sets the
    // "shipHit" flag. Once that flag is set "Draw" applies the effect to the ship and draws any
    // animation associated with that hit then deletes the PickUp once it is hit.
    public void Draw(){
        // While this pick up remains un-hit draw it
        if (!shipHit) {
            DrawPickUp();
        }
        else{
            // Draw the effect on the ship and apply it
            if (!effectDone) {
                DrawAndApplyEffect(shipThatHitThis);
            }
            else { // Once the effect has been applied delete this PickUp
                Dispose();
                // Delete the texture on the sprite we created
                image.getTexture().dispose();
            }
        }
    }
    // =============================================================================================
}
