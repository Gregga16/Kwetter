package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.UserBean;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class KwetterApplication extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//
//        classes.add(UserResource.class);
//
//        return classes;
//    }
}
