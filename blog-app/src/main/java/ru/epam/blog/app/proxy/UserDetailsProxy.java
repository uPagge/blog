package ru.epam.blog.app.proxy;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.epam.blog.core.entity.Person;
import ru.epam.blog.core.entity.enums.PersonGroup;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsProxy {

    public static UserDetails getProxy(Person person) {
        InvocationHandler invocationHandler = new MyUserDetails(person);
        Class[] classes = new Class[]{UserDetails.class};
        return (UserDetails) Proxy.newProxyInstance(UserDetailsService.class.getClassLoader(), classes, invocationHandler);
    }

    static class MyUserDetails implements UserDetails, InvocationHandler {

        private transient Person person;

        private MyUserDetails(Person person) {
            this.person = person;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(this, args);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<Role> roles = new HashSet<>();
            for (PersonGroup personGroup : person.getPersonGroups()) {
                switch (personGroup) {
                    case ADMIN:
                        roles.add(Role.ADMIN);
                        break;
                    case USER:
                        roles.add(Role.USER);
                        break;
                }
            }
            return roles;
        }

        @Override
        public String getPassword() {
            return person.getPassword();
        }

        @Override
        public String getUsername() {
            return person.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

    enum Role implements GrantedAuthority {

        USER, ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
