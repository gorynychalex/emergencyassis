# Backend for Emergency Assistant

Project for Help Hard Up People

There are "Needy" and "HardUp" the same

### Models of objects

 - TaskSocialService - assign task object
 - SocialService - Social service object
 - User - object
 
 ### Controllers
 
 - ServiceController
 - TaskController
 - UserController

### Examples of web requests:

 - User controller
```
var users; fetch('http://localhost:8080/api/v1/user').then(u=>u.json()).then(u=>users=u)
```

 - Service controller
 
 ```
 var services; fetch('http://localhost:8080/api/v1/service').then(s=>s.json()).then(s=>services=s)
 ```
 
  - Task POST
```
var task1={"id":"1000","socialService": services[3],"needy": users[2]}
fetch('http://localhost:8080/api/v1/task',{method:'POST',body:JSON.stringify(task1),headers: new Headers({'Content-type': 'application/json'})})
```

  - Task POST (new)
  ````
  var taskbyid={"uid":2,"sid":3}
  fetch('http://localhost:8080/api/v1/task/new',{method:'POST',body:JSON.stringify(taskbyid),headers: new Headers({'Content-type': 'application/json'})})
  ````