(function () {
/*
    $("#generateReportsLink").bind('mouseup', function () {
        //window.location.href = generateReportsPage;

        $.ajax({
            type: editId ? "PUT" : "POST",
            url: '/relay?s=items' + (editId ? "/" + editId : ""),
            data: JSON.stringify(jObject),
            contentType: "application/json",
            dataType: "json",
            success : function(data, textStatus, request) {
                console.log(request.getResponseHeader('Location'));
            }
        });
    });

    $.getJSON(
        "/relay?s=items?q=",
        {},
        function (array, textStatus, jqXHR) {
            loadArrayToTable(array);
        }
    );
*/
    $('#edit_sample_page').on('click', function() {
        window.location.href = "editor";
    });
})();
