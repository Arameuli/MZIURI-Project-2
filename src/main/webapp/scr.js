//function openForm(formId) {
//    document.getElementById('sendMessageForm').style.display = 'none';
//    document.getElementById('registerForm').style.display = 'none';
//    document.getElementById('messagesForm').style.display = 'none';
//
//    document.getElementById(formId).style.display = 'block';
//}

async function register(){
    var username=document.getElementById("username").value;
    var password= document.getElementById("password").value;
    var url="/messenger"+"/register"+'?username=username'+'password=password';
    var response = await fetch(url, { method: "POST" });
    console.log(response.ok);
    if(response.ok){
        alert(username+" has registered "+response.status);
    }
    else{
        alert("Could not register user: "+response.status);
    }
    deleteValueById("username", "password");
}

async function Message(){
    var user=document.getElementById("user").value;
    var message= document.getElementById("message").value;
    var url="/messenger"+"/message"+'?user=user'+'message=message';
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        alert("message has been sent to "+user+" "+response.status);
    }
    else{
        alert("Could not send message: "+response.status);
    }
    deleteValueById("user", "message");
}

async function getmessage(){
    var username1=document.getElementById("username1").value;
    var password1= document.getElementById("password1").value;
    var url="/messenger"+"/message"+'?username=username1'+'password=password1';
    var response = await fetch(url, { method: "GET" });
    if(!response.ok) {
        alert("username cannot be found " + username1 + " " + response.status);
    }
    deleteValueById("user", "message");

    var body = await response.text();
    var message=body.split('\n');
    var messagelist="";
    for(var i=0; i<message.length; i++){
        if(message[i]!=""){
            messagelist+=`<li>${message[i]}</li>`;
        }
    }
    var div = document.getElementById("messages");
    div.innerHTML = `<ul>${messagelist}</ul>`;
}

function deleteValueById(id, id1){
    document.getElementById(id).value=""
    document.getElementById(id1).value=""
}