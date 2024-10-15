package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:spring/db.properties")
@MapperScan("user.dao")
public class SpringConfiguration {
	private @Value("${jdbc.driver}") String driver;
	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	
	@Autowired
	private ApplicationContext appContext; // 환경 설정 파일임으로 주입 가능
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		// interface SqlSessionFactory & class SqlSessionFactoryBean
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		// 설정 파일
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		
		// xml 파일에서 타입 별명 짓기! >> 이름은 class 명으로됨
		sqlSessionFactoryBean.setTypeAliasesPackage("*.bean");
		
		
		// [매퍼 1개 ]
		sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mapper/userMapper.xml"));
		
		// [ 매퍼 2개 이상 ]
		// 방법1
		// 여러 개의 mapper.xml 파일 등록
//	    sqlSessionFactoryBean.setMapperLocations(
//	    			new ClassPathResource("mapper/userMapper.xml"),
//	    			new ClassPathResource("mapper/fileMapper.xml")
//	    );
		
	    // 방법2
		// 패턴을 이용한 여러 개의 매퍼 등록 (예: mapper 디렉토리 내의 모든 XML 파일)
		sqlSessionFactoryBean.setMapperLocations(
					appContext.getResources("classpath:mapper/*Mapper.xml")
				);
	    
		return sqlSessionFactoryBean.getObject(); // getObject(): SqlSessionFactory 변환
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSsessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		
		return sqlSsessionTemplate;
	}
	
	@Bean
	// 트랜잭션 매니저 설정
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
		
		return dataSourceTransactionManager;
	}
	
}