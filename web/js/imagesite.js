/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const showComments = () => {
    let url = 'http://10.114.32.21:8080/dogestram/db/service/addcomment';
    fetch(url).then(response =>
        response.json())
  .then((out) => {
      console.log(out);
      const iframe = document.querySelector('#iframe').contentWindow.document;
iframe.open();
iframe.write(out);
iframe.close();
  });
  };


showComments();


/*
const fetchJson = () => {  
let url = 'http://10.114.32.21:8080/dogestram/db/service/addcomment';

fetch(url)
.then(res => res.json())
.then((out) => {
  console.log('Checkout this JSON! ', out);
})
.catch(err => { throw err });
};
*/




