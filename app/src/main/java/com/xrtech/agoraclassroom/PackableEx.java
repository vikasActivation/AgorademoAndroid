package com.xrtech.agoraclassroom;

public interface PackableEx extends Packable{
    void unmarshal(ByteBuf in);
}
