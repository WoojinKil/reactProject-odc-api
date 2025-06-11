package com.odc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("com.odc.**.repository")
public class MyBatisConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
	    factory.setDataSource(dataSource);
	
	    // 여기서 DTO 패키지를 typeAlias로 등록
	    factory.setTypeAliasesPackage("com.odc.**.dto");
	    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
	    configuration.setMapUnderscoreToCamelCase(true);
	    factory.setConfiguration(configuration);
	    Resource[] resources = new PathMatchingResourcePatternResolver()
	            .getResources("classpath:/com/odc/**/*.xml");
	        factory.setMapperLocations(resources);

	    return factory.getObject();
	}

}
