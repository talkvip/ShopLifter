(function () {
    $("#generateReportsLink").bind('mouseup', function () {
        //window.location.href = generateReportsPage;
        // Ajax call.
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/eccms/items",
            data: JSON.stringify(testData),
            success: function () {
                alert("Success!");

            },
            contentType: "application/json",
            dataType: "json"
        });
    });

})();
