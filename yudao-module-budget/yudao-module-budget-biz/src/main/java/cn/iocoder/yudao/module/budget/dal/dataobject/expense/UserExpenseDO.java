package cn.iocoder.yudao.module.budget.dal.dataobject.expense;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 支出 DO
 *
 * @author budget
 */
@TableName("expense")
@KeySequence("expense_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExpenseDO extends BaseDO {

    /**
     * 支出id
     */
    @TableId
    private Integer id;
    /**
     * 预算id
     */
    private Integer budgetId;
    /**
     * 支出的项目
     */
    private String item;
    /**
     * 支出的金额
     */
    private BigDecimal expenseAmount;
    /**
     * 支出的年月
     */
    private LocalDate expenseDate;

}
