package com.ZakAdv.observer;

public interface SubjectOfChange {

    void register(Observer observer);

    void notifyObservers();

    void unregister(Observer observer);

}