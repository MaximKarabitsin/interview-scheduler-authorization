# interview-scheduler-authorization

## HTTP request examples

### Rule

``` 
# GET

# POST
fetch('/api/rule', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({name: 'test', description: 'desc', target:'1 == 1',condition: 'true', effect: true})}).then(console.log)

# PUT
fetch('/api/rule/1', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({id:1,name: 'ttt', target:'2 == 1', effect: true})}).then(console.log)

# DELETE
fetch('/api/rule/1', {method: 'DELETE'}).then(console.log)
```

### Policy
```
# POST
fetch('/api/policy', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({name: 'test', description: 'desc', target:'1 != 0', algorithm:'ALLOWED_IF_ALL_ALLOWED', rules:[{id:'65536'}, {id:'4'}, {id:'3'}]})}).then(console.log)

# PUT
fetch('/api/policy/1', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({name: 'ttttest', target:'8 != 0', algorithm:'ALLOWED_IF_ALL_ALLOWED', rules:[{id:'65536'}, {id:'4'}]})}).then(console.log)

#DELETE
fetch('/api/policy/1', {method: 'DELETE'}).then(console.log)
```

## Docker

```
# application.properties
spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/interviewsdb_dev

# command
mvn clean package
docker build -t <name-images:tag> .
docker run --name <name-container> -p 8080:8080 <name-images>

```