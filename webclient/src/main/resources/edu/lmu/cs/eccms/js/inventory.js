$(document).ready(function(){
    var testData = [
        {
            name:"Water Bottle",
            sku:23,
            description:"Plastic",
            price:3.4,
            dimension:{},
            itemHistory:{}
        },{
            name:"Paper Plates",
            sku:42,
            description:"Paper",
            price:5.2,
            dimension:{},
            itemHistory:{}
        }],

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
        };

    loadArrayToTable(testData);
    loadJsonArray();
});
