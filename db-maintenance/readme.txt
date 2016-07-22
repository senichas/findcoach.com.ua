update to current version:
mvn resources:resources liquibase:update -P local

rallback to 1 revision:
mvn liquibase:rollback -Dliquibase.rollbackCount=1

create a tag out of current revision
mvn resources:resources liquibase:tag -Dliquibase.tag=<tag name> -P local