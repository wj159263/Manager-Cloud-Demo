package com.wj.desiner._04adapter;

public class Adapter implements Target {
    OldThing oldThing;

    public Adapter(OldThing oldThing) {
        this.oldThing = oldThing;
    }

    @Override
    public void start() {
        this.oldThing.run();
    }
}
