package com.encore.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Spring Boot 애플리케이션의 시작점(Entry Point)을 나타낸다.
public class BlogBApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogBApplication.class, args);
		// SpringApplication.run(BlogBApplication.class, args) 메서드가 호출되면, Spring Boot 애플리케이션이 시작된다.
		// Application-context를 설정하고 실행하며, 내부적으로 필요한 빈들을 로드하고 초기화한다.
		// 이래서 framework도 공부해야한다. 자동 실행, 설정 내용을 모르면 나중에 어떻게 고치겠는가.
	}

}

/* 부트를 이용하면 아래에 있는 것들 다 필요 없고, yml 파일에 db 연결만 시켜주면 된다.
 <?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <!-- 데이터베이스 이름 및 계정 확인 -->
    <bean id="dataSource"
          class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/db_codingrecipe?useSSL=false&amp;serverTimezone=Asia/Seoul" />
        <property name="username" value="user_codingrecipe"/>
        <property name="password" value="1234"/>
    </bean>
    <!-- 파일 모두 생성했는지 확인 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value ="classpath:/mybatis-config.xml" />
        <property name="mapperLocations" value="classpath:/mapper/*.xml" />
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory" />
    </bean>
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8" />
        <property name="maxUploadSize" value="10000000" />
    </bean>
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
 */
