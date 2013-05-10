/**
 * Purpose: Manages client-side functions for Inventory.html.
 * Author:  Andrew Won
 */
$(document).ready(function(){
    var FORM_HEIGHT = 400,
        FORM_WIDTH = 350,
        DELETE_FORM_HEIGHT = 200,
        tableColumns = ["sku", "name", "price", "quantity", "description"],
        editId,
        stringBuild,

        /**
         * Creates a new table cell given contents and an ID to assign.
         * @param id ID of the new given table cell
         * @param contents Content to populate the new table cell with
         */
        tableCellFromIdAndContents = function (id, contents) {
                return "<td id=\"" + id + "\">" + contents + "</td>\n";
            },

        /**
         * Creates a new set of clickable icons for deleting and editing a table
         * row.
         * @param id ID of the row to which the tools will apply
         */
        tableRowToolsFromId = function (id) {
                return "<span style=\"float:right\"><img id=\"edit" + id + "\" class=\"edit\" " +
                    "src=\"/resources/edu.lmu.cs.eccms.Inventory/img/edit_icon.png\" " +
                    "alt=\"Edit Icon\"/><img id=\"trash" + id + "\" class=\"trash\"" +
                    "src=\"/resources/edu.lmu.cs.eccms.Inventory/img/trash_can.png\" " +
                    "alt=\"Delete Icon\"/></span>" + "</td>\n</tr>\n";
            },

        /**
         * Takes a formatted array and populates the inventory table with the
         * elements of the array.
         * @param arr Array to populate the inventory table with
         */
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

        /**
         * Performs AJAX call for all inventory items from web service.  Then
         * passes the JSON array to loadArrayToTable().
         */
        loadJsonArray = function () {
                $.getJSON(
                    "/relay?s=items?q=",
                    {},
                    function (array, textStatus, jqXHR) {
                        loadArrayToTable(array);
                    }
                );
            },

        sku = $('#sku'),
        name = $('#name'),
        price = $('#price'),
        quantity = $('#quantity'),
        description = $('#description'),
        allFields = $([]).add(name).add(sku).add(price).add(quantity).add(description),
        tips = $('.validateTips'),

        /**
         * Updates visual feedback on page.
         */
        updateTips = function(t) {
                tips
                    .text(t)
                    .addClass("ui-state-highlight");
                setTimeout(function() {
                    tips.removeClass("ui-state-highlight", 1500);
                }, 500);
            },

        /**
         * Ensures that a given object is not null.
         * @param o Object to check if null
         * @param n Name of object that is being checked; for error message
         */
        notNull = function(o, n) {
                if (!(o.val()) || o.val().length === 0) {
                        o.addClass('ui-state-error');
                        updateTips(n + ' cannot be empty, or is invalid.');
                    return false;
                } else {
                    return true;
                }
            },

        /**
         * Checks if a given object is a number.
         * @param o Object to check if it is a number
         * @param n Name of object that is being checked
         */
        checkIsNumber = function(o, n) {
                if (isNaN(o.val())) {
                    o.addClass('ui-state-error');
                    updateTips(n + ' must be a number.');
                    return false;
                } else {
                    return true;
                }
            },

        /**
         * Retrieves all items from displayed inventory table.
         * @param o Table of all items in visible table
         * @returns String JSON version of all items in inventory table
         */
        getItemsFromTable = function(o) {
                var jsonString = "{";
                for (var i = 0; i < o.length; i++) {
                    jsonString += "\"" + tableColumns[i] + "\":\""+ o[i].value + "\",";
                }
                jsonString = jsonString.substr(0, jsonString.length - 1);
                return jsonString + "}";
            };

    /**
     * Dialog handler for inventory item editing.
     */
    $('#dialog-form').dialog({
        autoOpen: false,
        height: FORM_HEIGHT,
        width: FORM_WIDTH,
        modal: true,
        buttons: {
            "Create or Edit an item": function() {
                var bValid = true,
                    jObject;
                allFields.removeClass('ui-state-error');

                bValid = bValid && notNull(name, 'Name');
                bValid = bValid && notNull(sku, 'SKU');
                bValid = bValid && notNull(price, 'Price');
                bValid = bValid && notNull(quantity, 'Quantity');

                bValid = bValid && checkIsNumber(price, 'Price');
                bValid = bValid && checkIsNumber(quantity, 'Quantity');

                if (bValid) {
                    var jsonString = getItemsFromTable(allFields);
                    jObject = $.parseJSON(jsonString);
                    $.ajax({
                        type: editId ? "PUT" : "POST",
                        url: '/relay?s=items' + (editId ? "/" + editId : ""),
                        data: JSON.stringify(jObject),
                        contentType: "application/json",
                        dataType: "json"
                    });

                    // Remove the existing row
                    if (editId) {
                        $('#' + editId).remove();
                    }
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

    /**
     * Dialog handler for delete item verification prompt.
     */
    $('#dialog-delete').dialog({
        autoOpen: false,
        height: DELETE_FORM_HEIGHT,
        width: FORM_WIDTH,
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

    // When the page loads, automatically load the items in the web service to
    // the table.
    loadJsonArray();

    $("#create").on('click', function() {
        $('#dialog-form').dialog('open');
    });
});
