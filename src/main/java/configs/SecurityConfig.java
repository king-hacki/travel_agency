package configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UsernamePasswordAuthenticationFilter authenticationFilter;

    @Autowired
    public void setAuthenticationFilter(@Lazy UsernamePasswordAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        String code = "user1Pass";
        String encode = passwordEncoder().encode(code);
        System.out.println("encode = " + encode);
        System.out.println("code = " + code);
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(encode)
                .roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().cors().disable()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/user_manager/**")
                .hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, exc) -> res.sendRedirect("/login"))
                .and()
                .exceptionHandling()
                .accessDeniedHandler((req, res, exc) -> res.sendRedirect("/access-denied"));
//                .and()
//                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

}
