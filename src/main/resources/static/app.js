document.addEventListener('DOMContentLoaded', function() {
    // Get recipient from URL query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const recipient = urlParams.get('user');
    
    if (!recipient) {
        alert('No recipient specified in URL. Please use ?user=username in the URL.');
        return;
    }
    getData();
    // Set chat title
    document.getElementById('chat-title').textContent = `Chat with ${recipient}`;
    
    // DOM elements
    const messageInput = document.getElementById('message-input');
    const sendBtn = document.getElementById('send-btn');
    const chatMessages = document.getElementById('chat-messages');
    
    // STOMP client setup
    //const socket = new WebSocket('ws://localhost:8080/ws');
   // const stompClient = Stomp.over(socket);
    const client=new StompJs.Client();
    client.brokerURL='http://localhost:8080/ws';
    // Connect to WebSocket
    client.onConnect=function(frame) {
        console.log('Connected: ' + frame);
        // Subscribe to the user's personal queue
        client.subscribe('/user/queue/direct', function(message) {
            if(message.headers['sender']==recipient){
            displayMessage(message.headers['sender'], message.body, 'incoming');}
            console.log('Received a message -'+message.body+'- from '+message.headers['sender']);
        });
    }
    client.onError=function(error) {
        console.error('STOMP error: ' + error);
    }
    client.activate();
    // Send message
    function sendMessage() {
        const content = messageInput.value.trim();
        if (content) {      
            client.publish({destination:'/app/direct/'+recipient, body:content});
            displayMessage('You', content, 'outgoing');
            console.log('Sent a message -'+content+'- to '+recipient);
            messageInput.value = '';
        }
    }
    
    // Event listeners
    sendBtn.addEventListener('click', sendMessage);
    messageInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });
    
    // Display message in chat
    function displayMessage(sender, content, direction) {
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', direction);
        
        const senderElement = document.createElement('div');
        senderElement.classList.add('sender-name');
        senderElement.textContent = sender;
        
        const contentElement = document.createElement('div');
        contentElement.textContent = content;
        
        messageElement.appendChild(senderElement);
        messageElement.appendChild(contentElement);
        
        chatMessages.appendChild(messageElement);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }
    async function getData() {
        const url = "http://localhost:8080/chat/history/"+recipient;
        try {
            const response = await fetch(url);
            if (!response.ok) {
             throw new Error(`Response status: ${response.status}`);
         }
         const history=await response.json();
         history.forEach(message => {
            if (message.sender==recipient){
            displayMessage(message.sender, message.text, 'incoming');}
            else displayMessage('You', message.text, 'outgoing');
         });
          
        } catch (error) {
            console.error(error.message);
        }}
});