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
                    .removeAttr('contenteditable', 'true')
                    .removeClass('cke_editable')
                    .removeClass('cke_editable_inline')
                    .removeClass('cke_contents_ltr')
                    .ckeditorGet().config.readOnly = true;
            }
        },
        cancelEvent = function( evt ) {
            evt.cancel();
        };

    CKEDITOR.editor.prototype.readOnly = function(isReadOnly) {
        // Turn off contentEditable.
        this.document.$.body.disabled = isReadOnly;
        CKEDITOR.env.ie ? this.document.$.body.contentEditable = !isReadOnly
                : this.document.$.designMode = isReadOnly ? "off" : "on";

        // Prevent key handling.
        this[ isReadOnly ? 'on' : 'removeListener' ]( 'key', cancelEvent, null, null, 0 );
        this[ isReadOnly ? 'on' : 'removeListener' ]( 'selectionChange', cancelEvent, null, null, 0 );

        // Disable all commands in wysiwyg mode.
        var command,
            commands = this._.commands,
            mode = this.mode;

        for ( var name in commands ) {
           command = commands[ name ];
           isReadOnly ? command.disable() : command[ command.modes[ mode ] ? 'enable' : 'disable' ]();
           this[ isReadOnly ? 'on' : 'removeListener' ]( 'state', cancelEvent, null, null, 0 );
        }
    }

    $('#edit_sample_page').on('click', function() {
        window.location.href = "editor";
    });

    $('#draw_off').on('click', function() {
        Boxes.turnOff();
        CkEditor.turnOn();
//        CKEDITOR.instances[0].readOnly( false );
    });

    $('#draw_on').on('click', function() {
        CkEditor.turnOff();
        Boxes.turnOn();
//        CKEDITOR.instances[0].readOnly( true );
    });
})();
