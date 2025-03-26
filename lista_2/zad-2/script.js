document.getElementById("packageForm").addEventListener("submit", function(event) {
    event.preventDefault();
    let form = event.target;
    //  (c) sprawdzanie poprawności formularza 
    if (!form.checkValidity()) {
        form.classList.add("was-validated");
        return;
    }
    let name = document.getElementById("name").value;
    let width = parseInt(document.getElementById("width").value);
    let height = parseInt(document.getElementById("height").value);
    let depth = parseInt(document.getElementById("depth").value);
    let volume = ((width * height * depth) / 1000000).toFixed(2); // (e) obliczanie objętości
    
    let row = `<tr><td>${name}</td><td>${width}</td><td>${height}</td><td>${depth}</td><td>${volume}</td></tr>`;
    document.getElementById("packageList").innerHTML += row;
    
    // sumaryczna objętości sumarycznej
    let total = [...document.querySelectorAll("#packageList td:last-child")].reduce((sum, td) => sum + parseFloat(td.textContent), 0);
    document.getElementById("totalVolume").textContent = total.toFixed(2);
    
    // (d) resetowanie formularza
    form.reset();
    form.classList.remove("was-validated");
});
// (g) wyszukiwanie paczek
document.getElementById("search").addEventListener("input", function() {
    let searchValue = this.value.toLowerCase();
    document.querySelectorAll("#packageList tr").forEach(row => {
        row.style.display = row.textContent.toLowerCase().includes(searchValue) ? "" : "none";
    });
});


