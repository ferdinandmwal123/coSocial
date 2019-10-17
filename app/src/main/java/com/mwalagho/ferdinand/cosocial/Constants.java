package com.mwalagho.ferdinand.cosocial;

import com.firebase.client.Firebase;

public final class Constants {
    private Firebase mRef;

    public Firebase getmRef() {
        return mRef = new Firebase("https://imfree-e0aef.firebaseio.com/");
    }
    public Firebase hereIsMref(){
        return mRef;
    }
}
