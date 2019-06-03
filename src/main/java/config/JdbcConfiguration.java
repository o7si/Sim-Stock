//package config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//public class JdbcConfiguration {
//
//    @Value("${jdbc.driverClassName}")
//    private String driverClassName;
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.password")
//    private String password;
//
//    /**
//     * 创建JdbcTemplate对象
//     * @param dataSource    数据源对象
//     * @return              JdbcTemplate对象
//     */
//    @Bean(name = "jdbcTemplate")
//    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    /**
//     * 创建数据源（DataSource）对象
//     * @return  数据源对象（DataSource）
//     */
//    @Bean(name = "dataSource")
//    public DataSource createDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//}
