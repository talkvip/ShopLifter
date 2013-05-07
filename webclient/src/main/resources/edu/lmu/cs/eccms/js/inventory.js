$(document).ready(function(){
    var tableColumns = ["sku", "name", "price", "quantity", "description"],
        editId,
        stringBuild,

        tableCellFromIdAndContents = function (id, content) {
                return "<td id=\"" + id + "\">" + contents + "</td>\n";
            },

        tableRowToolsFromId = function (id) {
                return "<span style=\"float:right\"><img id=\"edit" + id + "\" class=\"edit\" "
                    "src=\"/resources/edu.lmu.cs.eccms.Inventory/img/edit_icon.png\" " +
                    "alt=\"Edit Icon\"/><img id=\"trash" + id + "\" class=\"trash\"" +
                    "src=\"/resources/edu.lmu.cs.eccms.Inventory/img/trash_can.png\" " +
                    "alt=\"Delete Icon\"/></span>" + "</td>\n</tr>\n";
            },

        loadArrayToTable = function (arr) {
                for (i = 0; i < arr.length; i++) {
                    stringBuild = "<tr id=\"" + arr[i].id + "\">\n";
                    for (j = 0; j < tableColumns.length; j++) {
                        stringBuild += tableCellFromIdAndContents(tableColumns[j] + arr[i].id,
                                arr[i][tableColumns[j]]);
                    }
                    stringBuild = stringBuild.substr(0, stringBuild.length - 6);
                    stringBuild += tableRowToolsFromId(arr[i].id);
                    $("#inv-table > tbody:last").after(stringBuild);

                    $('.edit').on('click', function() {
                        var currentId = parseInt($(this).attr('id').substr(4));
                        for (j = 0; j < tableColumns.length; j++) {
                            $('#' + tableColumns[j]).val($('#' + tableColumns[j] + currentId).text());
                        }
                        editId = currentId;
                        $('#dialog-form').dialog('open');
                    });
                    $('.trash').on('click', function() {
                        editId = $(this).attr('id').substr(5);
                        $('#dialog-delete').dialog('open');
                    });
                }
            },

        loadJsonArray = function () {
                $.getJSON(
                    "/relay?s=items?q=",
                    {},
                    function (array, textStatus, jqXHR) {
                        loadArrayToTable(array);
                    }
                );
            },

        jsonString = "",
        jObject,
        sku = $('#sku'),
        name = $('#name'),
        price = $('#price'),
        quantity = $('#quantity'),
        description = $('#description'),
        allFields = $([]).add(name).add(sku).add(price).add(quantity).add(description),
        tips = $('.validateTips'),

        updateTips = function(t) {
                tips
                    .text( t )
                    .addClass( "ui-state-highlight" );
                setTimeout(function() {
                    tips.removeClass( "ui-state-highlight", 1500 );
                }, 500 );
            },

        notNull = function(o, n) {
                if (!(o.val()) || o.val().length === 0) {
                        o.addClass('ui-state-error');
                        updateTips(n + ' cannot be empty, or is invalid.');
                    return false;
                } else {
                    return true;
                }
            },

        checkIsNumber = function(o, n) {
                if (isNaN(o.val())) {
                    o.addClass('ui-state-error');
                    updateTips(n + ' must be a number.');
                    return false;
                } else {
                    return true;
                }
            };

    $('#dialog-form').dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Create or Edit an item": function() {
                var bValid = true;
                allFields.removeClass('ui-state-error');

                bValid = bValid && notNull(name, 'Name');
                bValid = bValid && notNull(sku, 'SKU');
                bValid = bValid && notNull(price, 'Price');
                bValid = bValid && notNull(price, 'Quantity');

                bValid = bValid && checkIsNumber(price, 'Price');
                bValid = bValid && checkIsNumber(quantity, 'Quantity');
                console.log(editId);

                if (bValid) {
                    var num = false;
                    jsonString = "{";
                    for (var i = 0; i < allFields.length; i++) {
                        num = i === 2;
                        jsonString += "\"" + tableColumns[i] + "\":\""+ allFields[i].value + "\",";
                    }

                    if (editId) {
                        jsonString += "\"id\":\"" + editId + "\"";
                    } else {
                        jsonString = jsonString.substr(0, jsonString.length - 1);
                    }
                    jsonString += "}";
                    jObject = $.parseJSON(jsonString);
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
                    loadArrayToTable([jObject]);
                    $(this).dialog('close');
                }
            },
            Cancel: function() {
                    $(this).dialog('close');
                }
            },
        close: function() {
                editId = null;
                allFields.val('').removeClass('ui-state-error');
            }
        });

    $('#dialog-delete').dialog({
        autoOpen: false,
        height: 200,
        width: 350,
        modal: true,
        buttons: {
            "Yes": function() {
                    $.ajax({
                        url: "/relay?s=items/" + editId,
                        type: 'DELETE'
                    });
                    $("#" + editId).remove();
                    $(this).dialog('close');
            },
            Cancel: function() {
                    $(this).dialog('close');
                }
            },
        close: function() {
                editId = null;
            }
        });

    loadJsonArray();

    $("#create").on('click', function() {
        $('#dialog-form').dialog('open');
    });
});
