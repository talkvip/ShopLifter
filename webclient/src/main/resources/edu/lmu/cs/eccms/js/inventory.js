$(document).ready(function(){
    var tableColumns = ["sku", "name", "price", "description"],
        editId,
        loadArrayToTable = function (arr) {
                for (i = 0; i < arr.length; i++) {
                    $("#inv-table > tbody:last").after(
                        "<tr id=\"" + arr[i].id + "\">\n" +
                        "<td id=\"sku" + arr[i].id + "\">" + arr[i].sku + "</td>\n" +
                        "<td id=\"name" + arr[i].id + "\">" + arr[i].name + "</td>\n" +
                        "<td id=\"price" + arr[i].id + "\">" + arr[i].price + "</td>\n" +
                        "<td id=\"quantity" + arr[i].id + "\">" + "NA" + "</td>\n" +
                        "<td id=\"description" + arr[i].id + "\">" + arr[i].description +
                        "<span style=\"float:right\">" +
                        "<img id=\"edit" + arr[i].id +
                        "\" class=\"edit\" src=\"/resources/edu.lmu.cs.eccms.Inventory/img/edit_icon.png\" alt=\"Edit Icon\"/>" +
                        "<img id=\"trash" + arr[i].id +
                        "\" class=\"trash\" src=\"/resources/edu.lmu.cs.eccms.Inventory/img/trash_can.png\" alt=\"Delete Icon\"/></span>" + "</td>\n" +
                        "</tr>\n"
                    );
                    $('.edit').on('click', function() {
                        var currentId = parseInt($(this).attr('id').substr(4));
                        $('#sku').val($('#sku' + currentId).text());
                        $('#name').val($('#name' + currentId).text());
                        $('#price').val($('#price' + currentId).text());
                        $('#description').val($('#description' + currentId).text());
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
        name = $('#name'),
        sku = $('#sku'),
        price = $('#price'),
        description = $('#description'),
        allFields = $([]).add(name).add(sku).add(price).add(description),
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

                bValid = bValid && checkIsNumber(price, 'Price');
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
