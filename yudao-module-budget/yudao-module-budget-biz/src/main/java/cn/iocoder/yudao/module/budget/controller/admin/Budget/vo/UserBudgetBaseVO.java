package cn.iocoder.yudao.module.budget.controller.admin.Budget.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 预算 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class UserBudgetBaseVO {

    @Schema(description = "预算类别")
    private String category;

    @Schema(description = "预算金额")
    private BigDecimal amount;

    @Schema(description = "预算金额")
    private BigDecimal spentAmount;

    @Schema(description = "预算的日期")
    private LocalDate budgetDate;

}
