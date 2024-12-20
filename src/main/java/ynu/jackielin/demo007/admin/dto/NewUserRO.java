package ynu.jackielin.demo007.admin.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ynu.jackielin.demo007.common.TrimString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "新增用户所需的请求体对象")
public class NewUserRO {
    @Schema(description = "用户代码", maxLength = 20)
    @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimString.class)
    public String code;
    @Schema(description = "用户姓名", maxLength = 20)
    @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimString.class)
    public String name;
    @Schema(description = "用户密码")
    @NotEmpty
    public String password;

}
