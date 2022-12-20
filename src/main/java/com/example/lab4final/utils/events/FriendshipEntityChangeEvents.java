package com.example.lab4final.utils.events;

import com.example.lab4final.domain.Friendship;

public class FriendshipEntityChangeEvents implements Event{
    private ChangeEventType type;
    private Friendship data, oldData;

    public FriendshipEntityChangeEvents(ChangeEventType type, Friendship data) {
        this.type = type;
        this.data = data;
    }
    public FriendshipEntityChangeEvents(ChangeEventType type, Friendship data, Friendship oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Friendship getData() {
        return data;
    }

    public Friendship getOldData() {
        return oldData;
    }
}
