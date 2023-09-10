'use strict';

window.addEventListener('DOMContentLoaded', function() {
    let basicUrl = 'https://localhost:8080/element/chatPage/';

    let listItem = document.querySelectorAll(".title"),
        windowElement = document.querySelector(".window"),
        windowClose = document.querySelector(".modal-close"),
        overlay = document.querySelector(".overlay-modal");

    function menuBehavior() {
        listItem.forEach(item => {
            item.addEventListener("click", (e) => {
                e.preventDefault();
                showModal();
                getForm(item);
            });
        });
    }

    windowClose.addEventListener("click", (e) => {
        closeModal();
    });

    overlay.addEventListener('click', () => {
        document.querySelector(".window.show").classList.remove("show");
        overlay.classList.remove("show");
    })

    document.addEventListener('keydown', (e) => {
        if (e.code === "Escape") {
            closeModal();
        }
    });

    const postData = async (url) => {
        const res = await fetch(url, {
            method: "GET",
            headers: {
                'Content-type': 'application/json'
            }
        });
        return await res.json();
    }

    function getForm(listItem) {
        let url;
        if (listItem.innerHTML === "Profile") {
            url = 'profile';
            postData(url)
                .then(data => {
                    console.log(data);
            });
        } else if(listItem.innerHTML === "Contacts") {
            url = 'contacts';
            getProfileForm(url);
        } else if (listItem.innerHTML === "New folder") {
            url = 'add-folder';
            getProfileForm(url);
        } else {
            url = 'settings';
            getProfileForm(url);
        }
    }

/*    function sendRequest(url) {

/!*        let response;

        let xch = new XMLHttpRequest();
        xch.open("GET", basicUrl + url);
        xch.responseType = 'json';

        xch.send();

        xch.onload = function() {
            response = JSON.parse(xch.response);
        }

        return response;*!/
    }*/


    function getProfileForm(url) {
        let response = sendRequest(url);
        let array = {};
        for (let key in response) {
            array.push(response[key]);
        }
    }

    function showModal() {
        windowElement.classList.add("show");
        overlay.classList.add("show");
    }

    function closeModal() {
        windowElement.classList.remove("show");
        overlay.classList.remove("show");
    }

    menuBehavior();
});