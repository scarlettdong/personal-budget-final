package cn.iocoder.yudao.module.budget.dal.dataobject.Budget;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预算 DO
 *
 * @author budget
 */
@TableName("budget")
@KeySequence("budget_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBudgetDO extends BaseDO {

    /**
     * 预算id
     */
    @TableId
    private Integer id;
    /**
     * 预算类别
     */
    private String category;
    /**
     * 预算金额
     */
    private BigDecimal amount;
    /**
     * 预算金额
     */
    private BigDecimal spentAmount;
    /**
     * 预算的日期
     */
    private LocalDate budgetDate;

}
