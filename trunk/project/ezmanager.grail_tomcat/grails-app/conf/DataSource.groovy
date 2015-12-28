dataSource {
	pooled = true
	driverClassName = "oracle.jdbc.driver.OracleDriver"
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			username = "ezmanager_dev"
			password = "ezmanager"
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:oracle:thin:@localhost:1521:xe"
		}
	}
	test {
		dataSource {
			username = "ezmanager_test"
			password = "ezmanager"
			dbCreate = "update"
			url = "jdbc:oracle:thin:@localhost:1521:xe"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}
