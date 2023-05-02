package com.tntp.minecraftmodapi;

public enum Turnary {
    TRUE, FALSE, UNCERTAIN;
    public boolean value() {
        return (this == TRUE);
    }

}
