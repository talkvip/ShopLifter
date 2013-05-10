/**
 * Purpose: JavaScript handler for both EditPage.html and Editor.html
 * Author:  Andrew Won
 */
(function () {
        /**
         * Loads saved divs from web service.
         */
    var loadDivs = function(array) {
                for (var i in array) {
                    for (var j in array[i]) {
                        if (!document.getElementById(array[i][j].name)) {
                            $('#drawing-area').append('<div id=' + array[i][j].name + '></div>');
                            idCounter++;
                            $('#' + array[i][j].name)
                                .addClass('box')
                                .addClass('box-highlight')
                                .css('height', array[i][j].height)
                                .css('width', array[i][j].width)
                                .css('left', array[i][j].cssleft)
                                .css('top', array[i][j].top)
                                .html(array[i][j].data)
                                .on('mousemove', Boxes.highlight)
                                .on('mousemove', Boxes.cursorChange)
                                .on('mouseleave', Boxes.unhighlight)
                                .on('mousedown', Boxes.startMoveOrResize);
                        }
                    }
                }
            },
        /**
         * Retrieves saved data from web service which will be displayed to user.
         */
        onLoad = function() {
                $.getJSON(
                    "/relay?s=sites?q=",
                    {},
                    function (array, textStatus, jqXHR) {
                        loadDivs(array);
                    }
                );
            },

        /**
         * Get the CSS needed by the Editor for a given element ID.
         * @param id ID of element for which the top, left, width, and height
         *           are to be retrieved
         * @returns CSS of requested element in JSON format
         */
        getDivCss = function (id) {
            var jObject = $('#' + id);
            return {
                    "top" : jObject.css('top'),
                    "left" : jObject.css('left'),
                    "width" : jObject.css('width'),
                    "height" : jObject.css('height')
                }
            },

        /**
         * CKEditor start and stop functionality
         */
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


        $.ajax({
            url: "/relay?s=sites",
            type: 'DELETE'
        });

        if (window["drawing-area"]) {
            Boxes.turnOff();
            CkEditor.turnOn();
            $('#draw_off').attr('disabled', 'disabled');
            $('#draw_on').removeAttr('disabled');
        }

        $(".inactive-drawing-area").find("div").each(function () {
            ids.push(this.id);
        });
        for (var i = 0; i < ids.length; i++) {
            divs[ids[i]] = getDivCss(ids[i]);
        }
        for ( var i in CKEDITOR.instances) {
            var name = CKEDITOR.instances[i].name;
            divs[name]["data"] = CKEDITOR.instances[i].getData();
        }

        if (!jQuery.isEmptyObject(divs)) {
            for (var i in divs) {
                var div = {};
                div["cssleft"] = divs[i]["left"];
                div["data"] = divs[i]["data"];
                div["height"] = divs[i]["height"];
                div["name"] = i;
                div["top"] = divs[i]["top"];
                div["width"] = divs[i]["width"];
                $.ajax({
                    type: "POST",
                    url: '/relay?s=sites',
                    data: JSON.stringify(div),
                    contentType: "application/json",
                    dataType: "json"
                });
            }
        }
    });

    onLoad();

})();
