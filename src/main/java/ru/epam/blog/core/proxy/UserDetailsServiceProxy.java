package ru.epam.blog.core.proxy;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.epam.blog.core.service.PersonService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserDetailsServiceProxy {

    private UserDetailsServiceProxy() {
        throw new IllegalStateException("Utility class");
    }

    public static UserDetailsService getProxy(PersonService personService) {
        InvocationHandler invocationHandler = new MyUserDetailService(personService);
        Class[] classes = new Class[]{UserDetailsService.class};
        return (UserDetailsService) Proxy.newProxyInstance(UserDetailsService.class.getClassLoader(), classes, invocationHandler);
    }

    static class MyUserDetailService implements UserDetailsService, InvocationHandler {

        private PersonService personService;

        private MyUserDetailService(PersonService personService) {
            this.personService = personService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(this, args);
        }

        @Override
        public UserDetails loadUserByUsername(String login) {
            return UserDetailsProxy.getProxy(personService.getByLogin(login));
        }
    }

}
