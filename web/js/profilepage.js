'use strict';

/*
Function to show images. One has to get images as json.
get: servletName?kategoria=2
 */
const showImages = (jsonImages) => {
    const ul = document.querySelector('ul');
    fetch(jsonImages).then((response) => {
        return response.json();
    }).then((json) => {
        json.forEach((image) => {
            let li = document.createElement("li");
            let img = document.createElement("IMG");
            let figure = document.createElement("figure");
            let figurecaption = document.createElement("figcaption");
            let a = document.createElement("a");

            let figc= document.createTextNode(image.mediaTitle);

            a.href =""; /*HREF NEEDED*/
            img.src = ""; /*SRC NEEDED*/

            a.appendChild(img);
            figurecaption.appendChild(figc);
            figure.appendChild(a);
            figure.appendChild(figurecaption);
            li.appendChild(figure);
            ul.appendChild(li);
        });
    });
};

/*
Get the elements and adding
 */

const home = document.querySelector("#home");
const search = document.querySelector("#search");


home.addEventListener("click",(clicky) => {
    console.log("home");
    window.location.href ="index.html";
});


search.addEventListener("click",(clicky) => {
    console.log("search");
});

