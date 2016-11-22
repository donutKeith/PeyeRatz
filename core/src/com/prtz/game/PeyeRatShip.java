package com.prtz.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

import KeithTools.Collider;
import KeithTools.CollisionDetection;

/**
 * Created by Keith on 8/19/2016.
 */
public class PeyeRatShip {

    // ATTRIBUTES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    private Sprite shipImage;
    private Collider ship;
    private float moveSpeed;

    // Individual Cannon attributes
    private float cannonRange;
    private int cannonCapacity;
    private float cannonLoadTime;
    private float cannonPower;

    private Vector<Cannon> shipCannons;
    private int numberOfCannons;

    private float width, height;

    private CollisionDetection collider;
    private Collider testBlock;

    // Debugging variables ---------------
    private ShapeRenderer debugingRenderer;

    private Collider cannonBall;
    // -----------------------------------
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    public PeyeRatShip (float startX, float startY, String imageName, int numberOfCannons){
        this.moveSpeed = 100;
        //this.shipImage = new Sprite(new Texture(Gdx.files.internal(imageName)));
        this.width = 10;
        this.height = 10;
        //this.shipImage.setSize(width, height);
        //this.shipImage.setOriginCenter();

        //this.shipImage.setCenter(startX, startY);



        this.numberOfCannons = numberOfCannons;
        this.shipCannons = new Vector<Cannon>(this.numberOfCannons);

        for(int i = 0; i < this.numberOfCannons; ++i){
            this.shipCannons.add(new Cannon(cannonCapacity, cannonRange, cannonLoadTime, cannonPower));
        }

        this.debugingRenderer = new ShapeRenderer();

        //Vector2[] points1 = { new Vector2(this.shipImage.getX(), this.shipImage.getY()), new Vector2(this.shipImage.getX() + width, this.shipImage.getY()), new Vector2(this.shipImage.getX() + width, this.shipImage.getY() + height), new Vector2(this.shipImage.getX(), this.shipImage.getY() + height)};
        //Vector2[] points2 = { new Vector2(10 , 10), new Vector2(20 , 10), new Vector2(20 , 20), new Vector2(10 , 20)};

        Vector2[] points1 = {new Vector2(10 , 10), new Vector2(10 , -10), new Vector2(-10 , -10), new Vector2(-10 , 10)};
        Vector2[] points2 = {new Vector2(10 , 10), new Vector2(10 , -10), new Vector2(-10 , -10), new Vector2(-10 , 10)};
        this.ship = new Collider(points1, "badlogic.jpg", new Vector2(50, 50), 10, 10, debugingRenderer);
        this.cannonBall = new Collider(new Vector2(20, 20), 10, debugingRenderer);
        this.testBlock = new Collider(points2, "badlogic.jpg", new Vector2(20, 20), 5, 5, debugingRenderer);
        // Collider Creatation
        collider = new CollisionDetection();
        //System.out.println("Ship Image x,y: " + GetCenterX() + ", " + GetCenterY() +  " | start X: " + startX + ", startY: " + startY );
    }

    public Collider GetCannonBall(){
        return cannonBall;
    }

    public void Draw(SpriteBatch sb){

       // System.out.println("Points1: (Ship image)");
       // for (Vector2 v : points1){
       //     System.out.println(v);
       // }
       // System.out.println("Points2:");
       // for (Vector2 v : points1){
       //     System.out.println(v);
       // }
        sb.end();
        this.debugingRenderer.setProjectionMatrix(GameScreen.gameCam.combined);
        debugingRenderer.begin(ShapeRenderer.ShapeType.Line);

        //this.testBlock.DebugDraw(debugingRenderer);
        System.out.println("Is colliding:                                                            " + this.ship.isCollidingWithCircle(cannonBall.Debug_GetCenter(), cannonBall.radius));
        this.ship.DebugDraw();
        this.cannonBall.DebugDraw();

        //System.out.println("Boxes are colliding: " + this.ship.isCollidingWith(this.testBlock));

        //if (this.ship.isCollidingWith(this.testBlock)){
            //System.out.println("IS COLLIDING");
        //}
        //else{
            //System.out.println("NOT COLLIDING");
        //}
        //if (collider.isColliding(points1, points2, debugingRenderer)){
        //    System.out.println("Colliding");
        //    shipImage.setTexture(new Texture(Gdx.files.internal("badlogic.jpg")));
        //}
        //else{
        //    shipImage.setTexture(new Texture(Gdx.files.internal("EnemyFrog.png")));
        //    System.out.println("Not");
        //}


        /*debugingRenderer.triangle(this.shipImage.getX() - 5, this.shipImage.getY() + height * 2, this.shipImage.getX() + width + 5, this.shipImage.getY() + height * 2, this.shipImage.getX() + width / 2f, this.shipImage.getY() + height * 2 + 10);
        debugingRenderer.rect(this.shipImage.getX(), this.shipImage.getY(), width, height);
        debugingRenderer.rect(this.shipImage.getX() - 5 ,this.shipImage.getY() + height, width + 10, height);
        debugingRenderer.rect(this.shipImage.getX() - 5 ,this.shipImage.getY() - height, width + 10, height);
        debugingRenderer.rect(this.shipImage.getX() - 9 ,this.shipImage.getY() - height * 2, width + 18, height);*/
        //this.debugingRenderer.setColor(Color.RED);
        //debugingRenderer.rect(this.ship.GetSprite().getX(), this.ship.GetSprite().getY(), width, height);
        //this.debugingRenderer.setColor(Color.BLUE);
        //debugingRenderer.rect(10, 10, 10, 10);
        debugingRenderer.end();
        sb.begin();
    }

    public Collider GetShip(){
        return ship;
    }

    public float GetShipSpeed(){
        return moveSpeed;
    }

    //public Sprite GetShip(){
    //    return shipImage;
    //}

    public void Dispose(){
        this.ship.GetSprite().getTexture().dispose();
    }
}
