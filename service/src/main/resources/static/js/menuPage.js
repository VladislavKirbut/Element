'use strict';

window.addEventListener('DOMContentLoaded', function() {
    let basicUrl = 'https://localhost:8080/element/chatPage/';

    let listItem = document.querySelectorAll(".title"),
        windowElement = document.querySelectorAll(".window"),
        windowClose = document.querySelectorAll(".modal-close"),
        overlay = document.querySelector(".overlay-modal"),
        folderList = document.querySelector(".folder-link"),
        secondModal = document.querySelector(".window-2");


    function menuBehavior() {
        listItem.forEach((item, i) => {
            item.addEventListener("click", (e) => {
                e.preventDefault();
                showModal(i);
            });
        });
    }

    function getFolders() {
        folderList.addEventListener("click", (e) => {
            e.preventDefault();
            secondModal.classList.add("show");
        })
    }

    function clickOnCloseBtn() {
        windowClose.forEach((closeBtn, i) => {
            closeBtn.addEventListener("click", (e) => {
                closeModal(i);
                let showWindow = document.querySelector(".window-2.show");
                if (showWindow != null && showWindow.classList.contains("show")) {
                    showWindow.classList.remove("show");
                }
            });
        });
    }

    overlay.addEventListener('click', () => {
        document.querySelector(".window.show").classList.remove("show");
        let showWindow = document.querySelector(".window-2.show");
        if (showWindow != null && showWindow.classList.contains("show")) {
            showWindow.classList.remove("show");
        }
        overlay.classList.remove("show");
    });

    document.body.addEventListener('keydown', (e) => {
        if (e.code === "Escape") {
            document.querySelector('.window.show').classList.remove('show');
            document.querySelector('.overlay').classList.remove('show');
            document.querySelector('.window-2.show').classList.remove('show');
        }
    });

    function showModal(modelOrder) {
        windowElement[modelOrder].classList.add("show");
        overlay.classList.add("show");
    }

    function closeModal(modelOrder) {
        windowElement[modelOrder].classList.remove("show");
        overlay.classList.remove("show");
    }

    menuBehavior();
    clickOnCloseBtn();
    getFolders();
});