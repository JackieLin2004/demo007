package ynu.jackielin.demo007.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "修改密码时提交的请求体")
public class ChangePasswordRO {

    @NotEmpty
    @Schema(description = "旧密码", example = "123456")
    private String oldPassword;

    @NotEmpty
    @Schema(description = "新密码", example = "123456")
    private String newPassword;
}
