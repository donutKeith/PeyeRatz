package com.prtz.game;

import java.util.Vector;

/**
 * Created by Keith on 8/19/2016.
 */
public class Cannon {
    private Vector<Projectile> ammo;
    private int ammoCapacity;
    private float range;
    private float power;
    private float loadTime;
    private float rotation;

    private float curLoadTimer;

    public Cannon(int ammoCapacity, float range, float loadTime, float power){
        this.ammoCapacity = ammoCapacity;
        ammo = new Vector<Projectile>(this.ammoCapacity);

        // Load Cannon
        for (int i = 0; i < this.ammoCapacity; ++i){
            ammo.add(new Projectile());
        }

        this.range = range;
        this.loadTime = loadTime;
        this.curLoadTimer = this.loadTime;
        this.power = power;
    }

    // Fire a projectile from this cannon
    public void Fire(){

    }
}
