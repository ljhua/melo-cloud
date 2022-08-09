package me.melo.cloud.oauth.granter;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;import org.springframework.security.oauth2.provider.*;import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;import java.util.LinkedHashMap;import java.util.Map;/** * web端（系统管理端）图形校验码登录 */public class WebCaptchaPasswordTokenGranter extends ResourceOwnerPasswordTokenGranter {    public WebCaptchaPasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {        super(authenticationManager, tokenServices, clientDetailsService, requestFactory);    }    @Override    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());        String captcha = parameters.get("captcha");        // 从Redis取缓存的验证码        String cacheCaptcha = "1234";        if (cacheCaptcha == null || "".equals(cacheCaptcha)) {            throw new InvalidGrantException("验证码已失效，请重新获取");        }        if (!cacheCaptcha.equals(captcha)) {            throw new InvalidGrantException("验证码错误");        }        return super.getOAuth2Authentication(client, tokenRequest);    }}