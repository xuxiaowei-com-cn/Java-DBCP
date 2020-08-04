package cn.com.xuxiaowei;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBCP 示例
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class DbcpConfiguration {

    public static void main(String[] args) {

        DbcpConfiguration dbcpConfiguration = new DbcpConfiguration();

        dbcpConfiguration.mysql();

        dbcpConfiguration.oracle();

    }

    public void mysql() {

        // 创建连接池示例
        BasicDataSource dataSource = new BasicDataSource();

        // 设置连接池所需驱动
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());

        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dbcp?useSSL=false&serverTimezone=GMT%2B8");

        dataSource.setUsername("root");

        dataSource.setPassword("root");

        // 设置连接池初始连接数
        dataSource.setInitialSize(5);

        // 设置连接池最少的空闲连接数
        dataSource.setMinIdle(2);

        try (Connection connection = dataSource.getConnection()) {

            System.out.println(connection);

            PreparedStatement preparedStatement = connection.prepareStatement("select 3 * 4 as a");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int a = resultSet.getInt("a");
                System.out.println(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void oracle() {

        // 创建连接池示例
        BasicDataSource dataSource = new BasicDataSource();

        // 设置连接池所需驱动
        dataSource.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());

        dataSource.setUrl("jdbc:oracle:thin:@192.168.8.128:1521:ORCL");

        dataSource.setUsername("scott");

        dataSource.setPassword("orcl");

        // 设置连接池初始连接数
        dataSource.setInitialSize(5);

        // 设置连接池最少的空闲连接数
        dataSource.setMinIdle(2);

        try (Connection connection = dataSource.getConnection()) {

            System.out.println(connection);

            PreparedStatement preparedStatement = connection.prepareStatement("select 3 * 4 as a from dual");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int a = resultSet.getInt("a");
                System.out.println(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
