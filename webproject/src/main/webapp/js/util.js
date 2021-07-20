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