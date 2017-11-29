'use strict';

/*
Function to show images. One has to get images as json.

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

const trending = document.querySelector("#trending");
const fresh = document.querySelector("#fresh");
const home = document.querySelector("#home");
const user = document.querySelector("#user");
const search = document.querySelector("#search");

trending.addEventListener("click",(clicky) => {
    console.log("trending");
});

fresh.addEventListener("click",(clicky) => {
    console.log("fresh");
});

home.addEventListener("click",(clicky) => {
    console.log("home");
});

user.addEventListener("click",(clicky) => {
    console.log("user");
});

search.addEventListener("click",(clicky) => {
    console.log("search");
});

