package cn.iocoder.yudao.module.budget.service.expense;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.UserExpenseCreateReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.UserExpenseExportReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.UserExpensePageReqVO;
import cn.iocoder.yudao.module.budget.controller.admin.expense.vo.UserExpenseUpdateReqVO;
import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.dal.dataobject.expense.UserExpenseDO;
import cn.iocoder.yudao.module.budget.dal.mysql.Budget.UserBudgetMapper;
import cn.iocoder.yudao.module.budget.dal.mysql.expense.UserExpenseMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserExpenseServiceImplTest {

    @Mock
    private UserExpenseMapper mockUserExpenseMapper;
    @Mock
    private UserBudgetMapper mockUserBudgetMapper;

    @InjectMocks
    private UserExpenseServiceImpl userExpenseServiceImplUnderTest;


    @Test
    void testUpdateUserExpense_UserExpenseMapperSelectByIdReturnsNull() {
        // Setup
        final UserExpenseUpdateReqVO updateReqVO = new UserExpenseUpdateReqVO();
        updateReqVO.setBudgetId(0);
        updateReqVO.setExpenseAmount(new BigDecimal("0.00"));
        updateReqVO.setId(0);

        when(mockUserExpenseMapper.selectById(0)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userExpenseServiceImplUnderTest.updateUserExpense(updateReqVO))
            .isInstanceOf(ServiceException.class);
    }

    @Test
    void testDeleteUserExpense() {
        // Setup
        when(mockUserExpenseMapper.selectById(0)).thenReturn(UserExpenseDO.builder()
            .id(0)
            .build());

        // Run the test
        userExpenseServiceImplUnderTest.deleteUserExpense(0);

        // Verify the results
        verify(mockUserExpenseMapper).deleteById(0);
    }

    @Test
    void testDeleteUserExpense_UserExpenseMapperSelectByIdReturnsNull() {
        // Setup
        when(mockUserExpenseMapper.selectById(0)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> userExpenseServiceImplUnderTest.deleteUserExpense(0))
            .isInstanceOf(ServiceException.class);
    }

    @Test
    void testGetUserExpense() {
        // Setup
        final UserExpenseDO expectedResult = UserExpenseDO.builder()
            .id(0)
            .build();
        when(mockUserExpenseMapper.selectById(0)).thenReturn(UserExpenseDO.builder()
            .id(0)
            .build());

        // Run the test
        final UserExpenseDO result = userExpenseServiceImplUnderTest.getUserExpense(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }



    @Test
    void testGetUserExpensePage() {
        // Setup
        final UserExpensePageReqVO pageReqVO = new UserExpensePageReqVO();
        pageReqVO.setBudgetId(0);
        pageReqVO.setItem("item");
        pageReqVO.setExpenseAmount(new BigDecimal("0.00"));
        pageReqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        final PageResult<UserExpenseDO> expectedResult = new PageResult<>(Arrays.asList(UserExpenseDO.builder()
            .id(0)
            .build()), 0L);

        // Configure UserExpenseMapper.selectPage(...).
        final PageResult<UserExpenseDO> userExpenseDOPageResult = new PageResult<>(Arrays.asList(UserExpenseDO.builder()
            .id(0)
            .build()), 0L);
        final UserExpensePageReqVO reqVO = new UserExpensePageReqVO();
        reqVO.setBudgetId(0);
        reqVO.setItem("item");
        reqVO.setExpenseAmount(new BigDecimal("0.00"));
        reqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserExpenseMapper.selectPage(reqVO)).thenReturn(userExpenseDOPageResult);

        // Run the test
        final PageResult<UserExpenseDO> result = userExpenseServiceImplUnderTest.getUserExpensePage(pageReqVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserExpensePage_UserExpenseMapperReturnsNoItem() {
        // Setup
        final UserExpensePageReqVO pageReqVO = new UserExpensePageReqVO();
        pageReqVO.setBudgetId(0);
        pageReqVO.setItem("item");
        pageReqVO.setExpenseAmount(new BigDecimal("0.00"));
        pageReqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        // Configure UserExpenseMapper.selectPage(...).
        final UserExpensePageReqVO reqVO = new UserExpensePageReqVO();
        reqVO.setBudgetId(0);
        reqVO.setItem("item");
        reqVO.setExpenseAmount(new BigDecimal("0.00"));
        reqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserExpenseMapper.selectPage(reqVO)).thenReturn(PageResult.empty());

        // Run the test
        final PageResult<UserExpenseDO> result = userExpenseServiceImplUnderTest.getUserExpensePage(pageReqVO);

        // Verify the results
        assertThat(result).isEqualTo(PageResult.empty());
    }

    @Test
    void testGetUserExpenseList2() {
        // Setup
        final UserExpenseExportReqVO exportReqVO = new UserExpenseExportReqVO();
        exportReqVO.setBudgetId(0);
        exportReqVO.setItem("item");
        exportReqVO.setExpenseAmount(new BigDecimal("0.00"));
        exportReqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        final List<UserExpenseDO> expectedResult = Arrays.asList(UserExpenseDO.builder()
            .id(0)
            .build());

        // Configure UserExpenseMapper.selectList(...).
        final List<UserExpenseDO> userExpenseDOS = Arrays.asList(UserExpenseDO.builder()
            .id(0)
            .build());
        final UserExpenseExportReqVO reqVO = new UserExpenseExportReqVO();
        reqVO.setBudgetId(0);
        reqVO.setItem("item");
        reqVO.setExpenseAmount(new BigDecimal("0.00"));
        reqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserExpenseMapper.selectList(reqVO)).thenReturn(userExpenseDOS);

        // Run the test
        final List<UserExpenseDO> result = userExpenseServiceImplUnderTest.getUserExpenseList(exportReqVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserExpenseList2_UserExpenseMapperReturnsNoItems() {
        // Setup
        final UserExpenseExportReqVO exportReqVO = new UserExpenseExportReqVO();
        exportReqVO.setBudgetId(0);
        exportReqVO.setItem("item");
        exportReqVO.setExpenseAmount(new BigDecimal("0.00"));
        exportReqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});

        // Configure UserExpenseMapper.selectList(...).
        final UserExpenseExportReqVO reqVO = new UserExpenseExportReqVO();
        reqVO.setBudgetId(0);
        reqVO.setItem("item");
        reqVO.setExpenseAmount(new BigDecimal("0.00"));
        reqVO.setExpenseDate(new LocalDate[]{LocalDate.of(2020, 1, 1)});
        when(mockUserExpenseMapper.selectList(reqVO)).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserExpenseDO> result = userExpenseServiceImplUnderTest.getUserExpenseList(exportReqVO);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
