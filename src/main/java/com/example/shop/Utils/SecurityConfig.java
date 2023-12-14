package com.example.shop.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {  //extends WebSecurityConfigurerAdapter

    //@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Настройка пользователей для аутентификации
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password") // В реальном приложении используйте кодирование пароля
                .roles("USER");
    }

   // @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll() // URL доступные всем
//                .anyRequest().authenticated() // Остальные URL требуют аутентификации
//                .and()
//                .formLogin() // Использование формы входа
//                .loginPage("/login") // Страница входа
//                .permitAll()
//                .and()
//                .logout() // Настройка выхода
//                .permitAll();
//    }
}
