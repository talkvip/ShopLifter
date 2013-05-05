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
    var CkEditor = {
            turnOn: function () {
                var ids = [],
                    id;
                $(".inactive-drawing-area").find("div").each(function(){ ids.push(this.id); });

                for (var i = 0; i < ids.length; i++) {
                    // Make sure we only instantiate CKEditor for divs without instances
                    if (CKEDITOR.instances[ids[i]]) {
                        continue;
                    }
                    id = '#' + ids[i];

                    if($(id).is(':empty')) {
                        $(id).html('<p>Insert text here.</p>');
                    }

                    $(id).attr('contenteditable', 'true');

                    CKEDITOR.inline(ids[i]);
                }
            },
            turnOff: function () {
                $('.inactive-box')
                    .attr('contenteditable', 'false');
            }
        };

    $('#edit_sample_page').on('click', function() {
        window.location.href = "editor";
    });

    $('#draw_off').on('click', function() {
        Boxes.turnOff();
        CkEditor.turnOn();
    });

    $('#draw_on').on('click', function() {
        CkEditor.turnOff();
        Boxes.turnOn();
    });
})();
