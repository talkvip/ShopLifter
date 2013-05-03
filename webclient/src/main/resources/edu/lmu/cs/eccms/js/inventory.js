$(document).ready(function(){
    var tableColumns = ["sku", "name", "price", "description"],
        loadArrayToTable = function (arr) {
                for (i = 0; i < arr.length; i++) {
                    $("#inv-table > tbody:last").after(
                        "<tr id=\"" + arr[i].id + "\">\n" +
                        "<td>" + arr[i].sku + "</td>\n" +
                        "<td>" + arr[i].name + "</td>\n" +
                        "<td>" + arr[i].price + "</td>\n" +
                        "<td>" + "NA" + "</td>\n" +
                        "<td>" + arr[i].description +
                        "<span style=\"float:right\"><img id=\"trash" + arr[i].id +
                        "\" class=\"trash\" src=\"/resources/edu.lmu.cs.eccms.Inventory/img/trash_can.png\" alt=\"Delete Icon\"/></span>" + "</td>\n" +
                        "</tr>\n"
                    );

                    $('.trash').on('click', function(e) {
                        var currentId = $(this).attr('id').substr(5);
                        // TODO: Need to prompt user

                        $.ajax({
                            url: "/relay?s=items/" + currentId,
                            type: 'DELETE',
                          }).done(function (data) {
                              $("#" + currentId).remove();
                          });
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
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            "Create an item": function() {
                var bValid = true;
                allFields.removeClass('ui-state-error');

                bValid = bValid && notNull(name, 'Name');
                bValid = bValid && notNull(sku, 'SKU');
                bValid = bValid && notNull(price, 'Price');

                bValid = bValid && checkIsNumber(price, 'Price');

                if (bValid) {
                    var num = false;
                    jsonString += "{";
                    for (var i = 0; i < allFields.length; i++) {
                        num = i === 2;
                        jsonString += "\"" + tableColumns[i] + "\":\""+ allFields[i].value + "\",";
                    }
                    jsonString = jsonString.substr(0, jsonString.length - 1);
                    jsonString += "}";
                    jObject = $.parseJSON(jsonString);

                    $.ajax({
                        type: "POST",
                        url: '/relay?s=items',
                        data: JSON.stringify(jObject),
                        success: function () {
                            // TODO
                        },
                        contentType: "application/json",
                        dataType: "json"
                    });

                    $(this).dialog('close');
                }
            },
            Cancel: function() {
                    $(this).dialog('close');
                }
            },
        close: function() {
                allFields.val('').removeClass('ui-state-error');
            }
        });

    loadJsonArray();

    $("#create").on('click', function() {
        $('#dialog-form').dialog('open');
    });
});
