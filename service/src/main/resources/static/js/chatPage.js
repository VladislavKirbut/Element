 'use strict';

let formImg = document.querySelector(".form-file_block"),
    fileUploader = document.querySelector(".menu-form_file"),
    fileAction = document.querySelector(".file-action"),
    cameraIcon = document.getElementsByClassName("fa-camera")[0],
    formBtnApply = document.getElementsByClassName("form_btn-apply")[0],
    formBtnReset = document.getElementsByClassName("form_btn-reset")[0],
    formDelBtn = document.getElementsByClassName("del_btn")[0],
    applyBtn = document.getElementsByClassName("apply_btn")[0],
    crossPos = document.getElementsByClassName("cross_pos")[0],
    resetButtonIcon = document.getElementById("reset-button"),
    applyButtonIcon = document.getElementsByClassName("check_pos")[0],
    menuItem = document.getElementById("item");

let image = null;

function createImage() {
    fileUploader.addEventListener('change', (event) => {
        const reader = new FileReader();
        const files = event.target.files;
        const file = files[0];
        reader.readAsDataURL(file);

        reader.addEventListener('load', (event) => {
            const img = document.createElement('img');
            formImg.appendChild(img);
            img.classList.add("image");
            image = document.querySelector(".image");
            img.src = event.target.result;
            img.alt = file.name;
            img.height = 130;
            img.width = 130;
            img.style.borderRadius = 70 + "px";
            formImg.classList.remove("form-file_block");
            cameraIcon.classList.remove("fa", "fa-camera", "fa-position");
            fileUploader.style.display = "none";
            fileAction.style.display = "block";
        });
    });
}

    formBtnReset.addEventListener("click", evt => {
        image.remove();
        formDelBtn.value = null;
        fileUploader.value = fileUploader.defaultValue;
        formImg.classList.add("form-file_block");
        cameraIcon.classList.add("fa", "fa-camera", "fa-position");
        fileAction.style.display = "none";
        fileUploader.style.display = "block";
    });

    crossPos.addEventListener("click", evt => {
        fileUploader.value = fileUploader.defaultValue;
        image.remove();
        formDelBtn.value = null;
        formImg.classList.add("form-file_block");
        cameraIcon.classList.add("fa", "fa-camera", "fa-position");
        fileAction.style.display = "none";
    });

/*    async function elementUpdate(menuItem) {
        try {
            var html = await (await fetch(location.href)).text();
            var newdoc = new DOMParser().parseFromString(html, 'text/html');
            document.querySelector(menuItem).outerHTML = newdoc.querySelector(menuItem).outerHTML;
            console.log('Элемент '+menuItem+' был успешно обновлен');
            return true;
        } catch(err) {
            console.log('При обновлении элемента '+menuItem+' произошла ошибка:');
            console.dir(err);
            return false;
        }
    }*/

 createImage();