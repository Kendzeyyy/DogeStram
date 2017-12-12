/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';

function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin === -1) {
        begin = dc.indexOf(prefix);
        if (begin !== 0) return null;
    }
    else
    {
        begin += 2;
        var end = document.cookie.indexOf(";", begin);
        if (end === -1) {
        end = dc.length;
        }
    }
    // because unescape has been deprecated, replaced with decodeURI
    //return unescape(dc.substring(begin + prefix.length, end));
    return decodeURI(dc.substring(begin + prefix.length, end));
}

// checks user authentication by cookie value
const cookie = () => {

  let checkAuth = getCookie("id");

  if (checkAuth !== null) {
    let username = getCookie("id");
    /*
    user_name.innerHTML = 'Hello ' + username;
  } else {
      alert("Session expired, please re-login");
      window.location.replace("index.html");
  }*/
};

document.onload = cookie();


/*
const loginForm = document.querySelector('#loginForm');
loginForm.addEventListener("submit", (cliky) =>{
    cliky.preventDefault();
    let cookie = document.cookie;
    console.log(cookie.valueOf());
    //login();
    //window.location.href ="index.html";
});*/



/*
fetch('http://10.114.32.21:8080/dogestram/login.html').then((zap) => {
    console.log(zap);
    
});*/

/*
const writeCookie = (key, value) => {
    document.cookie = key + "=" + value + ";" + "path=/";
};
 
const getCookie = (cname) => {
    const name = cname + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
};
const loginForm = document.querySelector('#loginForm');
loginForm.addEventListener("submit", (cliky) =>{
    cliky.preventDefault(); 
fetch(  'login.html', {
                    headers: {
                        'auth-token': getCookie('auth-token') //an example of attaching a cookie value to request header
                        
                    },
                    credentials: 'include',
                    method: 'POST'		
	
                    //body: `username=${'#nameInput'}&password=${'#passwordInput'}`
                })
                .then((response) => {
                    return response.json();
                })
                .then((json) => {
                    console.log(json);
                    
                })
                .catch((err) => {
                    console.log(err);
                });
});*/






