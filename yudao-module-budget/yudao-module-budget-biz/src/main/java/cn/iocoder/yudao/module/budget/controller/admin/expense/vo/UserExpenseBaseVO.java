package cn.iocoder.yudao.module.budget.controller.admin.expense.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 支出 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class UserExpenseBaseVO {

    @Schema(description = "预算id", example = "5455")
    private Integer budgetId;

    @Schema(description = "支出的项目")
    private String item;

    @Schema(description = "支出的金额")
    private BigDecimal expenseAmount;

    @Schema(description = "支出的年月")
    private LocalDate expenseDate;

}
