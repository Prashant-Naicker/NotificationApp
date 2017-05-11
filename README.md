############### COMPLETE #################

# NotificationApp

At the end of this project, you will be able to send a message from your web browser and receive it as a notification on your android phone. Languages used: Go, Javascript, Java

**Message Sender - Javascript** - Uses AJAX to send an HTTP POST request to the web server that contains the notification message.

**Web Server - Golang** - Listens for HTTP requests from the Message Sender and Android App.  If the message is from the Message Sender, it queues the message.  If it is from the Android App, it replies with all the messages in the queue and then empties the queue.

**Android App - Java** - When opened, sends an HTTP POST to the server to get any messages.  Displays the messages as notifications or similar to the user.
