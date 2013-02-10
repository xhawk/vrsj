$(document).ready(function() {

    $.get("/vrsj", function(data, status) {
        jQuery.each(data, function(index) {
            var item = data[index];
            $("#name").append("<th class='span3'>" + item.name + "</th>");
            $("#today").append("<td>" + item.dayAvarage + "</th>");
            $("#month").append("<td>" + item.monthAvarage + "</th>");
        });
    });

});
