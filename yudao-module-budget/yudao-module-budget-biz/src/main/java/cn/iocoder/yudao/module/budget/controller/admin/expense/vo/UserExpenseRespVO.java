package cn.iocoder.yudao.module.budget.controller.admin.expense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 支出 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserExpenseRespVO extends UserExpenseBaseVO {

    @Schema(description = "支出id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24109")
    private Integer id;

}
