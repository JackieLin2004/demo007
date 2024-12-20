package ynu.jackielin.demo007.admin.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ynu.jackielin.demo007.common.TrimString;

import java.util.UUID;
@Data
@Schema(description = "更新用户基本信息的请求体对象")
public class UpdateUserRO {
    @NotNull
    public UUID id;

    @Schema(description = "用户代码", maxLength = 20)
    @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimString.class)
    public String code;

    @Schema(description = "用户姓名", maxLength = 20)
    @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimString.class)
    public String name;
}
