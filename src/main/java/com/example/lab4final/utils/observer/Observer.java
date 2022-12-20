package com.example.lab4final.utils.observer;


import com.example.lab4final.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}