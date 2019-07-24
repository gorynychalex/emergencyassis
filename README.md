# Backend for Emergency Assistant

[Emergency Assist Mobile Project](https://github.com/loakdv/EmergencyAssistantRefresh.git)

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
  
### Security config

##### Oauth2

  - Get token
  
  ````
  http -a client:secret --form POST http://localhost:8080/oauth/token grant_type='password' username='popovich' password='12345678'
  ````
  
  
  ````
  curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=popovich -d password=12345678
  ````
  ````
  let formdata = new FormData()
  let headers=new Headers()
  formdata.append('grant_type','password')
  formdata.append('username','popovich')
  formdata.append('password','12345678')
  headers.append('Authorization', 'Basic ' + btoa("client:secret"))
  var rr;
  fetch('http://localhost:8080/oauth/token', { method: 'POST', headers: headers, body: formdata }).then(r=>r.json()).then(r=>rr=r.access_token)
  ````
  
  - Get data with token:
  ````
  http http://localhost:8080/api/v1/service/ access_token=='bd7cc339-8627-47df-be33-d31b96f16a80'
  ````
  
  ````
  curl -H 'Authorization: Bearer bd7cc339-8627-47df-be33-d31b96f16a80' http://localhost:8080/api/v1/service
  ````
  
  ````
  fetch('/api/v1/service/',{ headers: {'Authorization': 'Bearer ' + rr}}).then(r=>r.json()).then(console.log)
  ````
  
  https://gist.github.com/ivermac/922def70ed9eaf83799b68ab1a587595
  
  
  ````
  var headers=new Headers(); var formdata=new FormData(); formdata.append('grant_type','password'); formdata.append('username','popovich'); formdata.append('password','12345678'); headers.append('Authorization', 'Basic ' + btoa("client:secret")); fetch('http://localhost:8080/oauth/token', { method: 'POST', headers: headers, body: formdata }).then(r=>r.json()).then(r=>localStorage.setItem('access_token',r.access_token)); localStorage.access_token;
  ````
  
  ````
  fetch('/api/v1/organization/1',{ headers: {'Authorization': 'Bearer ' + localStorage.access_token}}).then(r=>r.json()).then(console.log)

  ````
  
  ### 401 - ERROR !!! 
  ##### Access to fetch at /oauth/token from origin has been blocked by CORS policy!!!
  
  #####Fix:
  
  https://stackoverflow.com/questions/44625488/spring-security-cors-error-when-enable-oauth2
  
  