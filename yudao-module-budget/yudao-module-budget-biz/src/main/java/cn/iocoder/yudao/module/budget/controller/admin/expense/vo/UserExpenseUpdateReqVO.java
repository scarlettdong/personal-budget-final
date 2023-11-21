package cn.iocoder.yudao.module.budget.controller.admin.expense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 支出更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserExpenseUpdateReqVO extends UserExpenseBaseVO {

    @Schema(description = "支出id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24109")
    @NotNull(message = "支出id不能为空")
    private Integer id;

}
