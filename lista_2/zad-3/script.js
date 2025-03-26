// Klasa reprezentująca menedżera notatek
class NoteManager {
    constructor() {
        // (c) Ładowanie notatek z localStorage (przechowujemy dane w stanie aplikacji)
        this.notes = JSON.parse(localStorage.getItem('notes')) || [];
        this.renderNotes(); // Renderujemy notatki na stronie po załadowaniu
    }

    // (a) Tworzenie lub edycja notatki
    saveNote(id, title, content) {
        if (id) {
            // (b) Edycja notatki - znajdujemy notatkę po ID
            let note = this.notes.find(n => n.id === id);
            note.title = title;
            note.content = content;
        } else {
            // Tworzymy nową notatkę
            const newNote = { id: Date.now().toString(), title, content }; // (b) Unikalny identyfikator za pomocą Date.now()
            this.notes.push(newNote);  // Dodajemy notatkę do listy
        }
        this.updateStorage(); // (c) Zapisujemy stan notatek do localStorage
        this.renderNotes(); // Renderujemy listę notatek
    }

    // (c) Zapisanie notatek w localStorage
    updateStorage() {
        localStorage.setItem('notes', JSON.stringify(this.notes)); // Zapisujemy dane w localStorage
    }

    // (b) Renderowanie listy notatek na stronie
    renderNotes() {
        const notesList = document.getElementById('notesList');
        notesList.innerHTML = ''; // Czyścimy listę przed jej ponownym wyświetleniem

        // Wyświetlanie notatek
        this.notes.forEach(note => {
            const li = document.createElement('li');
            li.className = 'list-group-item d-flex justify-content-between align-items-center';
            li.textContent = note.title;
            li.onclick = () => loadNote(note.id); // (b) Kliknięcie powoduje edycję notatki
            notesList.appendChild(li);
        });
    }
}

// Tworzymy instancję menedżera notatek
const noteManager = new NoteManager();

// Obsługa formularza do zapisywania/edycji notatki
document.getElementById('noteForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const id = document.getElementById('noteId').value;
    const title = document.getElementById('noteTitle').value;
    const content = document.getElementById('noteContent').value;

    noteManager.saveNote(id, title, content); // Zapisujemy notatkę
    this.reset(); // Czyszczenie formularza po zapisaniu
});

// (b) Ładowanie notatki do formularza po kliknięciu na listę
function loadNote(id) {
    const note = noteManager.notes.find(n => n.id === id); // Szukamy notatki po ID
    document.getElementById('noteId').value = note.id;
    document.getElementById('noteTitle').value = note.title;
    document.getElementById('noteContent').value = note.content;
}

// (d) Eksportowanie notatek do pliku JSON
function exportNotes() {
    const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(noteManager.notes)); // Serializacja notatek do JSON
    const downloadAnchor = document.createElement('a');
    downloadAnchor.setAttribute("href", dataStr);
    downloadAnchor.setAttribute("download", "notes.json");
    document.body.appendChild(downloadAnchor);
    downloadAnchor.click(); // Uruchamiamy pobieranie pliku
    document.body.removeChild(downloadAnchor);
}

// (d) Importowanie notatek z pliku JSON
function importNotes(event) {
    const file = event.target.files[0];  // Pobieramy plik
    if (!file) return; 

    const reader = new FileReader();
    reader.onload = function (e) {
        try {
            const importedNotes = JSON.parse(e.target.result); // Parsujemy dane z pliku JSON
            if (Array.isArray(importedNotes)) {
                // (c) Łączymy nowe notatki z istniejącymi, nie nadpisując poprzednich
                noteManager.notes = noteManager.notes.concat(importedNotes);
                noteManager.updateStorage();  // Zapisujemy zaktualizowaną listę do localStorage
                noteManager.renderNotes();    // Odświeżamy widok notatek
            } else {
                alert("Nieprawidłowy format pliku - oczekiwano tablicy notatek.");
            }
        } catch (error) {
            alert("Błąd podczas importu pliku. Upewnij się, że plik ma poprawny format JSON.");
        }
    };

    reader.readAsText(file);  // Wczytujemy plik jako tekst
}
