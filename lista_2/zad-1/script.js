const slider = document.getElementById('slider');
const circle = document.getElementById('circle');

// Funkcja do aktualizacji koła
const updateCircle = (size) => {
    circle.style.width = circle.style.height = `${size}px`; // (c) Dynamiczna zmiana średnicy koła
    
    let r = Math.min(255, 161 + size * 0.5);  
    let g = Math.min(182, 13 + size * 0.3);   
    let b = Math.min(210, 90 + size * 0.4);

    circle.style.backgroundColor = `rgb(${r}, ${g}, ${b})`; // (f) Efekt kolorystyczny

    // Zmniejsz rozmiar tekstu
    const fontSize = size < 50 ? `${size / 2}px` : '20px';
    circle.style.fontSize = fontSize;
    circle.textContent = `${size}px`; //(d) Wyświetlanie śrendicy
};

// Domyślny rozmiar to 100px
slider.value = 100;
updateCircle(100);

// Aktualizacja koła na podstawie suwaka
slider.addEventListener('input', (e) => updateCircle(e.target.value));

// (g) Obsługa klawiatury do zmiany rozmiaru
document.addEventListener('keydown', (e) => {
    let size = parseInt(slider.value);
    if (e.key === 'ArrowRight' && size < 500) size += 5;
    if (e.key === 'ArrowLeft' && size > 10) size -= 5;
    slider.value = size;
    updateCircle(size);
});
