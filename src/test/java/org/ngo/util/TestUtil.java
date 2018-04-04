package org.ngo.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.ngo.user.model.UserProfile;
import org.ngo.util.util.ClassUtil;
import org.ngo.util.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static List<UserProfile> profileList;
    private int count = 5000000;

    @BeforeClass
    public static void setUp(){
        profileList = new ArrayList<>();
        UserProfile profile; // = new UserProfile();
        for (int i = 10 ; i>=0 ; i++){
            profile = new UserProfile();
            profile.setHomeAddress(RandomUtil.nextString(20));
            profileList.add(profile);
        }
    }

    @Test
    public void testReflect(){

    }

}
