window.onload = function() {
    const source = new EventSource("api/employees/messages");
    source.addEventListener("message",
        function(event) {
            const name = JSON.parse(event.data).employeeName;
            console.log(name);

            document.querySelector("#messages-div").innerHTML += `<p>Employee has been created: ${name}</p>`;
        });
}