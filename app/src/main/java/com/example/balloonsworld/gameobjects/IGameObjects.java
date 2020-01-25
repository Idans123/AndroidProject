package com.example.balloonsworld.gameobjects;

public interface IGameObjects {
    int getObjectY();
    int getObjectX();
    void update();
    ObjectType getType();
}
