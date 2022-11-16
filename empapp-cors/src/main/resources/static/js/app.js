window.onload = function() {

    let url = "http://localhost:8080/api/employees";
    fetch(url)
            .then(function(response) {
                return response.json();
                })
            .then(function(jsonData) {
                console.log(jsonData);
            });


}