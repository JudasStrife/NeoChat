// Maintain list of existing chats to prevent duplicates
const existingChats = new Set();
getData();
async function getData() {
        const url = "http://localhost:8080/chats/history";
        try {
            const response = await fetch(url);
            if (!response.ok) {
             throw new Error(`Response status: ${response.status}`);
         }
         const chats=await response.json();
         chats.forEach(chat => {
            displayChat(chat);
         });
          
        } catch (error) {
            console.error(error.message);
        }}
function displayChat(recipient) {
    // Normalize recipient name for case-insensitive comparison
    const normalizedRecipient = recipient.trim().toLowerCase();
    
    // Check if chat already exists
    if (existingChats.has(normalizedRecipient)) {
        alert(`Chat with ${recipient} already exists!`);
        return;
    }
    
    // Add to existing chats
    existingChats.add(normalizedRecipient);
    
    const chatsList = document.getElementById('chats-list');
    
    const chatBox = document.createElement('div');
    chatBox.className = 'chat-box-item';
    chatBox.onclick = () => {
        window.location.href = `/chat?user=${encodeURIComponent(recipient)}`;
    };
    
    const nameElement = document.createElement('div');
    nameElement.className = 'recipient-name';
    nameElement.textContent = recipient;
    
    chatBox.appendChild(nameElement);
    chatsList.appendChild(chatBox);
}

// Initialize with sample chats
document.addEventListener('DOMContentLoaded', () => {
    // Sample chat recipients
    const sampleChats = [];
    
    sampleChats.forEach(recipient => {
        existingChats.add(recipient);
        displayChat(recipient);
    });
    
    // Setup new user input
    const newUserInput = document.getElementById('new-user-input');
    
    newUserInput.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            const recipient = newUserInput.value.trim();
            if (recipient) {
                displayChat(recipient);
                newUserInput.value = ''; // Clear input
            }
        }
    });
    // Focus the input field on page load
    newUserInput.focus();
});