package cn.iocoder.yudao.module.budget.controller.admin.Budget.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预算 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserBudgetRespVO extends UserBudgetBaseVO {

    @Schema(description = "预算id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6455")
    private Integer id;

}
