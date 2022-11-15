window.onload = function() {
    const socket = new SockJS("/websocket-endpoint");
    const client = Stomp.over(socket);

    document.querySelector("#message-button").onclick = function() {
        const text = document.querySelector("#message-input").value;
        client.send("/app/messages", {}, JSON.stringify({"text": text}));
    };


    client.connect({}, function(frame) {
        client.subscribe("/topic/employees", function(frame) {
            console.log(frame);
            const text = JSON.parse(frame.body).text;
            document.querySelector("#message-div").innerHTML += `<p>${text}</p>`;
        });
    });
}