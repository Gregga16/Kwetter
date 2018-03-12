package com.gregory.kwetter.interceptor;

import com.gregory.kwetter.model.Kweet;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class KweetLoggedInterceptor {

    @AroundInvoke
    public Object interceptKweet(InvocationContext context)
            throws Exception {

        Kweet kweet = (Kweet) context.getParameters()[0];

        if (kweet != null) {
            String message = kweet.getMessage();

            kweet.setMessage(message
                    .replaceAll("bitch", "onaardig meisje")
                    .replaceAll("klootzak", "vervelende jongen")
                    .replaceAll("kut", "poesje")
                    .replaceAll("homo", "valt op jongens"));
        }

        return context.proceed();
    }
}
