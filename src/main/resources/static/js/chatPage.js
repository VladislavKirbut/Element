 'use strict';

let fileUploader = document.getElementsByClassName("addfile")[0],
    form = document.getElementsByClassName("form")[0],
    imgBlock = document.getElementsByClassName("img_block")[0],
    plus = document.getElementsByClassName("fa")[0],
    formBtn = document.getElementsByClassName("form_btn_apply")[0],
    formBtnReset = document.getElementsByClassName("form_btn_reset")[0],
    formDelBtn = document.getElementsByClassName("del_btn")[0],
    applyBtn = document.getElementsByClassName("apply_btn")[0],
    crossPos = document.getElementsByClassName("cross_pos")[0],
    resetButtonIcon = document.getElementById("reset-button"),
    applyButtonIcon = document.getElementsByClassName("check_pos")[0],
    menuItem = document.getElementById("item");

let image = null;

fileUploader.addEventListener('change', (event) => {
    const reader = new FileReader();
    const files = event.target.files;
    const file = files[0];
    reader.readAsDataURL(file);

    reader.addEventListener('load', (event) => {
        const img = document.createElement('img');
        form.appendChild(img);
        img.classList.add("image");
        image = document.getElementsByClassName("image")[0];
        img.src = event.target.result;
        img.alt = file.name;
        img.height = 130;
        img.width = 130;
        img.style.borderRadius = 70 + "px";
        imgBlock.classList.remove("img_block");
        plus.classList.remove("fa", "fa-plus", "fa-position");
        formBtn.style.display = "block";
        formBtnReset.style.display = "block";
        formDelBtn.style.display = "block";
        applyBtn.style.display = "block";
        resetButtonIcon.classList.add("fa", "fa-trash-o", "cross_pos");
        applyButtonIcon.classList.add("fa", "fa-check", "check_pos");
    });

    formBtnReset.addEventListener("click", evt => {
        image.remove();
        formDelBtn.value = null;
        fileUploader.value = fileUploader.defaultValue;
        imgBlock.classList.add("img_block");
        plus.classList.add("fa", "fa-plus", "fa-position");
        formBtnReset.style.display = "none";
        resetButtonIcon.classList.remove("fa", "fa-trash-o", "cross_pos");
        formBtn.style.display = "none";
        applyButtonIcon.classList.remove("fa", "fa-check", "check_pos");
    });

    crossPos.addEventListener("click", evt => {
        fileUploader.value = fileUploader.defaultValue;
        image.remove();
        formDelBtn.value = null;
        imgBlock.classList.add("img_block");
        plus.classList.add("fa", "fa-plus", "fa-position");
        formBtnReset.style.display = "none";
        resetButtonIcon.classList.remove("fa", "fa-trash-o", "cross_pos");
        formBtn.style.display = "none";
        applyButtonIcon.classList.remove("fa", "fa-check", "check_pos");
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
});