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
            const li = document.createElement("li");
            const img = document.createElement("IMG");
            const figure = document.createElement("figure");
            const figurecaption = document.createElement("figcaption");
            const a = document.createElement("a");

            const figc= document.createTextNode(image.mediaTitle);

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

