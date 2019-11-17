# interview-scheduler-authorization

## HTTP request examples

### Rule

``` bash
# POST
fetch('/api/rule', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({name: 'test', description: 'desc', target:'1 == 1',condition: 'true', effect: true})}).then(console.log)

# PUT
fetch('/api/rule/1', {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({id:1,name: 'ttt', target:'2 == 1', effect: true})}).then(console.log)

# DELETE
fetch('/api/rule/1', {method: 'DELETE'}).then(console.log)
```