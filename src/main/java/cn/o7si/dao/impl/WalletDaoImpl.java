package cn.o7si.dao.impl;

import cn.o7si.dao.IWalletDao;
import cn.o7si.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 钱包相关操作的持久层实现类
 */

@Repository("walletDaoImpl")
public class WalletDaoImpl implements IWalletDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 为账户添加一个钱包
     * @param accountId     账户ID
     * @return              如果添加成功则返回true，否则返回false
     */
    @Override
    public boolean addWallet(Integer accountId) {
        // 执行添加钱包时使用的SQL语句
        String sql = "insert into wallet(uaid) values(?)";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, accountId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 返回添加结果
        return rtValue == 1;
    }


    /**
     * 根据账户ID查询钱包
     * @param accountId     账户ID
     * @return              如果查询成功则返回钱包，否则返回null
     */
    @Override
    public Wallet findWalletByUaid(Integer accountId) {
        // 根据账户ID查询钱包时使用的SQL语句
        String sql = "select * from wallet where uaid=?";

        List<Wallet> wallets = null;
        try {
            // 执行SQL语句
            wallets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wallet.class), accountId);
        } catch (Exception ignored) {
            // 忽略异常
        }

        // 如果未查询到结果
        if (wallets == null || wallets.size() == 0)
            return null;

        // 如果结果集不唯一
        if (wallets.size() > 1)
            return null;

        // 返回查询结果
        return wallets.get(0);
    }

    /**
     * 根据账户ID查询钱包是否存在
     * @param accountId     账户ID
     * @return              如果查询成功则返回ture，否则返回false
     */
    @Override
    public boolean findExistByUaid(Integer accountId) {
        Wallet wallets = findWalletByUaid(accountId);
        System.out.println(wallets);
        return wallets != null;
    }

    /**
     * 更新钱包信息
     * @param wallet        钱包
     * @return              如果更新成功则返回true，否则返回false
     */
    @Override
    public boolean updateWallet(Wallet wallet) throws Exception {
        // 更新钱包时要使用的SQL语句
        String sql = "update wallet set balance=?, password=? where id=?";

        int rtValue = 0;
        try {
            // 执行SQL语句
            rtValue = jdbcTemplate.update(sql, wallet.getBalance(), wallet.getPassword(), wallet.getId());
        } catch (Exception ignored) {
            // 忽略异常
        }

        if (true) {
            throw new RuntimeException("手动异常");
        }
        // 返回更新结果
        return rtValue == 1;
    }
}
