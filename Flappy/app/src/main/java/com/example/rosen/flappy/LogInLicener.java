package com.example.rosen.flappy;

/**
 * Created by rosen on 12.11.14.
 */
public interface LogInLicener {
    public abstract void onLogIn(String name, String mail, String fac);
    public abstract void onDeath(int score);
}
