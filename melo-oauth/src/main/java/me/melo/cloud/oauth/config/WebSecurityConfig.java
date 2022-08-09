package me.melo.cloud.oauth.config;import lombok.RequiredArgsConstructor;import me.melo.cloud.oauth.provider.AppCaptchaPasswordTokenProvider;import me.melo.cloud.oauth.provider.AppSmsCodeTokenProvider;import me.melo.cloud.oauth.provider.WebSmsCodeTokenProvider;import me.melo.cloud.oauth.service.UserDetailsServiceImpl;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.dao.DaoAuthenticationProvider;import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;@Configuration@RequiredArgsConstructor@EnableWebSecuritypublic class WebSecurityConfig extends WebSecurityConfigurerAdapter {    private final UserDetailsServiceImpl userDetailsService;    @Bean    public AuthenticationManager authenticationManager() throws Exception {        AuthenticationManager authenticationManager = super.authenticationManager();        return authenticationManager;    }    @Bean    public WebSmsCodeTokenProvider webSmsCodeTokenProvider() {        WebSmsCodeTokenProvider webSmsCodeTokenProvider = new WebSmsCodeTokenProvider();        return webSmsCodeTokenProvider;    }    @Bean    public AppCaptchaPasswordTokenProvider appCaptchaPasswordTokenProvider() {        AppCaptchaPasswordTokenProvider appCaptchaPasswordTokenProvider = new AppCaptchaPasswordTokenProvider();        return appCaptchaPasswordTokenProvider;    }    @Bean    public AppSmsCodeTokenProvider appSmsCodeTokenProvider() {        AppSmsCodeTokenProvider appSmsCodeTokenProvider = new AppSmsCodeTokenProvider();        return appSmsCodeTokenProvider;    }    @Bean    public DaoAuthenticationProvider daoAuthenticationProvider() {        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();        daoAuthenticationProvider.setUserDetailsService(userDetailsService);        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());        return daoAuthenticationProvider;    }    @Override    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        auth.authenticationProvider(webSmsCodeTokenProvider());        auth.authenticationProvider(daoAuthenticationProvider());        auth.authenticationProvider(appCaptchaPasswordTokenProvider());        auth.authenticationProvider(appSmsCodeTokenProvider());    }    @Override    protected void configure(HttpSecurity http) throws Exception {        // 关闭csrf跨域检查        http.csrf().disable()                .authorizeRequests()                .anyRequest()                .authenticated()                .and()                .formLogin();    }    @Bean    public PasswordEncoder passwordEncoder() {        return new BCryptPasswordEncoder();    }}