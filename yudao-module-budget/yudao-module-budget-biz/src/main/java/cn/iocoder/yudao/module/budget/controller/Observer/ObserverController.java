package cn.iocoder.yudao.module.budget.controller.Observer;

import cn.iocoder.yudao.module.budget.dal.dataobject.Budget.UserBudgetDO;
import cn.iocoder.yudao.module.budget.service.ObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin-api/Observer")
public class ObserverController {
@Autowired
    private ObserverService observerService;

    @GetMapping("/getCategoryAndAmount")
    private List<UserBudgetDO> getCategoryAndAmount(){
        return  observerService.getCategoryAndAmount();
    }
    @GetMapping("/getCategorDate")
    private  List<UserBudgetDO> getDateAndAmount(){
        return observerService.getCateryDate();
    }
}
