package ynu.jackielin.demo007.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielin.demo007.security.service.JwkService;

import java.util.Map;

@RestController
@Tag(name = "Auth", description = "与身份认证相关的接口")
public class AuthController {

    private final JwkService jwkService;

    public AuthController(JwkService jwkService) {
        this.jwkService = jwkService;
    }

    @GetMapping("/auth/jwk")
    @Operation(summary = "获取公钥", description = "用于获取验证令牌数字签证")
    public Map<String, Object> GetJwk() {
        return this.jwkService.jwkSet().toJSONObject();
    }

}
