export function initializeCarousel() {
    let carouselInner = document.getElementById("aliens-carousel-inner");
    let images = JSON.parse(ALIEN_IMAGES);
    for(let i=0; i < images.length; i++) {
        let div = document.createElement("div");
        div.classList.add("carousel-item");
        div.id = "carousel-item-" + i;
        
        let img = document.createElement("img");
        img.setAttribute("src", "http://localhost:8080/webproject" + images[i]["image_url"]);
        img.classList.add("d-block", "w-100");

        div.appendChild(img);
        carouselInner.appendChild(div);
    }
    document.getElementById("carousel-item-0").classList.add("active");
}