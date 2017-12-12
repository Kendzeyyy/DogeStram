'use strict';

/*
Function to show images. One has to get images as json.
get: servletName?kategoria=2
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

const showImages = (url) => {
    const ul = document.querySelector('main ul');
    fetch(url).then((response) => {
        return response.json();
    }).then((json) => {
        json.forEach((image) => {
            let li = document.createElement("li");
            let img = document.createElement("IMG");
            let figure = document.createElement("figure");
            let figurecaption = document.createElement("figcaption");
            let a = document.createElement("a");

            let figc= document.createTextNode("doggy");

            a.href =""; /*HREF NEEDED*/
            img.src = `http://${image.path}`; /*SRC NEEDED*/

            a.appendChild(img);
            figurecaption.appendChild(figc);
            figure.appendChild(a);
            figure.appendChild(figurecaption);
            li.appendChild(figure);
            ul.appendChild(li);
        });
    });
};

showImages("http://10.114.32.21:8080/dogestram/db/service/photosbydate");

/*
Get the elements and adding
 */
const trending = document.querySelector("#trending");
const fresh = document.querySelector("#fresh");
const home = document.querySelector("#home");
const user = document.querySelector("#user");
const search = document.querySelector("#search");

trending.addEventListener("click",(clicky) => {
    console.log("trending");
    /*showImages("servletName?kategoria=2");*/
});

fresh.addEventListener("click",(clicky) => {
    console.log("fresh");
});

home.addEventListener("click",(clicky) => {
    console.log("home");

});

user.addEventListener("click",(clicky) => {
    console.log("user");
    window.location.href ="login.html";
});

search.addEventListener("click",(clicky) => {
    console.log("search");
});

