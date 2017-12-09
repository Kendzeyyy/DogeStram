/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';


const loggingForm = document.querySelector('#loggingin');
console.log(loggingForm);

const loggingButton = document.querySelector("#submitbutton");
console.log(loggingButton);
loggingButton.addEventListener("logging_in",(cliky) =>{
    window.location.href ="index.html";
});


/*
fetch('http://10.114.32.21:8080/dogestram/login.html').then((zap) => {
    console.log(zap);
});*/




