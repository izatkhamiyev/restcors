
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/v1/person/"
    }).then(function(data, status, people) {
        for(let i = 0; i < data.length; i++){
            $('#people').append("<li><a href='http://localhost:8080/api/v1/person/" 
            + data[i].id + "' >" + "name: " + data[i].name + "</a></li>\n" );
        }
    });    
});