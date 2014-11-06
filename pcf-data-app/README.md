sample application using
- Spring Boot
- Spring Data JPA
- Spring Web (RESTController)

front end is:
- Bootstrap
- JQuery

sample for deployment to Pivotal CloudFoundry with the following procedure;
- cf push [app-name] -p [war location] -m 512M --no-start
- cf create-service [cleardb] [spark] [db-name]
- cf bind-service [app-name] [db-name]
- cf start

demonstration suggestion;
- add a new field to Customer.java (e.g. emailAddress) including setters/getters
- rebuild (mvn clean package)
- push to cf (cf push [app-name] -p [war location]


