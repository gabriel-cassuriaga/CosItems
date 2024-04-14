function toggleForms() {
    var productForm = document.getElementById("productForm");
    var adminForm = document.getElementById("adminForm");
    if (productForm.style.display === "none") {
        productForm.style.display = "block";
        adminForm.style.display = "none";
    } else {
        productForm.style.display = "none";
        adminForm.style.display = "block";
    }
}