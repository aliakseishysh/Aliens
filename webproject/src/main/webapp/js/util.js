async function downloadImage(imageSrc, newImageName) {
    let fileList = await fetch(imageSrc)
        .then(res => res.blob())
        .then(blob => {
            const file = new File([blob], newImageName, blob);
            let dataTransfer = new DataTransfer();
            dataTransfer.items.add(file);
            return dataTransfer.files;
        });
    return fileList;
};

// $(document).ready(function () {
//     let dropdownMenuButton = document.getElementById("dropdownMenuButton")
//     dropdownMenuButton.addEventListener('click', function(event) {
//         document.getElementById("nav-dropdawn-container").classList.toggle("show");
//         document.getElementById("nav-dropdown-menu").classList.toggle("show");
//         if (dropdownMenuButton.getAttribute("aria-expanded") == true) {
//             dropdownMenuButton.setAttribute("aria-expanded", false);
//         } else {
//             dropdownMenuButton.setAttribute("aria-expanded", true);
//         };
//       }, false);
// });