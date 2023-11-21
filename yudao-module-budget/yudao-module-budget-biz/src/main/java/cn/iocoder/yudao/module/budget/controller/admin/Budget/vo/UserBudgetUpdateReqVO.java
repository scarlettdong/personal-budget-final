package cn.iocoder.yudao.module.budget.controller.admin.Budget.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 预算更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBudgetUpdateReqVO extends UserBudgetBaseVO {

    @Schema(description = "预算id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6455")
    @NotNull(message = "预算id不能为空")
    private Integer id;

}
