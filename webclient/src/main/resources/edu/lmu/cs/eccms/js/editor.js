/**
 * Javascript handler for both EditPage.html and Editor.html
 *
 * @author Andrew Won
 */
(function () {
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
                var ids = [];
                $(".inactive-drawing-area").find("div").each(function(){ ids.push(this.id); });
                for (var i = 0; i < ids.length; i++) {
                    if (CKEDITOR.instances[ids[i]]) {
                        CKEDITOR.instances[ids[i]].destroy();
                    }
                }
                $('.inactive-box')
                    .attr('contenteditable', 'false');
                for (i in CKEDITOR.instances) {

                }
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

    $('#save').on('click', function() {
        for (var i in CKEDITOR.instances) {
            console.log(CKEDITOR.instances[i].getData());
            console.log(CKEDITOR.plugins.undo.UndoManager.save());
        }
        alert('hi');
    });
})();
