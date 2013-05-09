/**
 * Javascript handler for both EditPage.html and Editor.html
 *
 * @author Andrew Won
 */
(function () {
    var getDivCss = function (id) {
            var jObject = $('#' + id);
            return {
                    "top" : jObject.css('top'),
                    "left" : jObject.css('left'),
                    "width" : jObject.css('width'),
                    "height" : jObject.css('height')
                }
            },
        CkEditor = {
            turnOn : function () {
                var ids = [], id;
                $(".inactive-drawing-area").find("div").each(function () {
                    ids.push(this.id);
                });

                for ( var i = 0; i < ids.length; i++) {
                    // Make sure we only instantiate CKEditor for divs without
                    // instances
                    if (CKEDITOR.instances[ids[i]]) {
                        continue;
                    }
                    id = '#' + ids[i];

                    if ($(id).is(':empty')) {
                        $(id).html('<p>Insert text here.</p>');
                    }

                    $(id).attr('contenteditable', 'true');

                    CKEDITOR.inline(ids[i]);
                }
            },
            turnOff : function () {
                var ids = [];
                $(".inactive-drawing-area").find("div").each(function () {
                    ids.push(this.id);
                });
                for ( var i = 0; i < ids.length; i++) {
                    if (CKEDITOR.instances[ids[i]]) {
                        CKEDITOR.instances[ids[i]].destroy();
                    }
                }
                $('.inactive-box').attr('contenteditable', 'false');
            }
        };

    $('#edit_sample_page').on('click', function () {
        window.location.href = "editor";
    });

    $('#draw_off').on('click', function () {
        Boxes.turnOff();
        CkEditor.turnOn();
        $('#draw_off').attr('disabled', 'disabled');
        $('#draw_on').removeAttr('disabled');
    });

    $('#draw_on').on('click', function () {
        CkEditor.turnOff();
        Boxes.turnOn();
        $('#draw_on').attr('disabled', 'disabled');
        $('#draw_off').removeAttr('disabled');
    });

    $('#save').on('click', function () {
        var divs = {}, jObject, ids = [];
        $(".inactive-drawing-area").find("div").each(function () {
            ids.push(this.id);
        });
        for (var i = 0; i < ids.length; i++) {
            divs[ids[i]] = getDivCss(ids[i]);
        }
        for ( var i in CKEDITOR.instances) {
            var name = CKEDITOR.instances[i].name;
            divs[name]["data"] = CKEDITOR.instances[i].getData();
            //console.log(divs);
        }
        // TODO: divs now contains minimum saved data for wysiwyg
    });
})();
