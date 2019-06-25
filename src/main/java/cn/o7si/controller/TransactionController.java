package cn.o7si.controller;

import cn.o7si.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 交易相关操作的控制层
 */
@RequestMapping("/transaction")
@Controller("transactionController")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;
}
