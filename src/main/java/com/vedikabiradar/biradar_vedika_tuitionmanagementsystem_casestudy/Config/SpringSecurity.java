package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Config;

//package com.vedikabiradar.sba_html_css_js.Config;
//
//
//import com.vedikabiradar.sba_html_css_js.Service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity {
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                                .requestMatchers("/userRegistration","/userRegistration/save").permitAll()
//                                .requestMatchers("/index").permitAll()
////                          .requestMatchers("/registered-art-collectors").hasRole("ADMIN")
//                                .requestMatchers("/registeredUsers").hasAnyRole("PARENT", "ADMIN")
//                                .requestMatchers("/static/css/**","/static/images/**","/static/JS/**").permitAll()
//                                .requestMatchers("/index").permitAll()
//                                .requestMatchers("/contact").permitAll()
//                                .requestMatchers("/about").permitAll()
//                                .requestMatchers("/loginparentadmin").permitAll()
//                                .requestMatchers("/contact/list").permitAll()
//                                .requestMatchers("/calculate").permitAll()
//                                .requestMatchers("/emailSubscription").permitAll()
//                                .requestMatchers("/emailSubscription/list").permitAll()
//                                .requestMatchers("/employeeRegistration","/employeeList").permitAll()
//                                .requestMatchers("register","/parent","/child/**").permitAll()
//
//                                .anyRequest().authenticated()
//                )
//                .formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/users")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//}




import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/userRegistration","/userRegistration/save").permitAll()
                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/registeredUsers","/adminDashboard","/deleteStudent","/deleteParent").hasRole( "ADMIN")
                        .requestMatchers("/parentDashboard","/userinfo","/studentInfo").hasAnyRole("PARENT")
                        .requestMatchers("/static/css/**","/static/images/**","/static/JS/**","/static/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/standardSubject").hasRole("ADMIN")
                        .requestMatchers("/standardSubject/list").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler())
                        .permitAll()
                ).logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String email = authentication.getName();
            if (email.endsWith("@admin.com")) {
                response.sendRedirect("/adminDashboard");
            } else {
                response.sendRedirect("/parentDashboard");
            }
        };
    }
}

